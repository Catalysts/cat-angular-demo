package cc.catalysts.angular.spring.exception.impl;

import cc.catalysts.angular.spring.client.ErrorDto;
import cc.catalysts.angular.spring.exception.ExceptionSerializer;
import cc.catalysts.angular.spring.exception.NotFoundException;
import cc.catalysts.angular.spring.exception.StatusCodeAndResponse;
import cc.catalysts.angular.spring.validation.ValidationException;
import cc.catalysts.angular.spring.validation.dto.ValidationErrorDto;
import cc.catalysts.angular.spring.web.util.BigDecimalParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Set;

/**
 * @author Klaus Lehner
 */
public class DefaultExceptionSerializer implements ExceptionSerializer {

    private final MessageSource messageSource;
    private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionSerializer.class);

    public DefaultExceptionSerializer(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public StatusCodeAndResponse
    getStatusAndResponse(Exception ex) {
        if (isOfType(ex, IllegalArgumentException.class)) {
            return new StatusCodeAndResponse(HttpStatus.NOT_ACCEPTABLE.value(),
                    new ErrorDto(ErrorDto.ErrorType.IllegalArgument, ex.getLocalizedMessage()));
        }
        if (isOfType(ex, MissingServletRequestParameterException.class,
                UnsatisfiedServletRequestParameterException.class,
                HttpRequestMethodNotSupportedException.class,
                ServletRequestBindingException.class)) {
            return new StatusCodeAndResponse(HttpStatus.NOT_ACCEPTABLE.value(),
                    new ErrorDto(ErrorDto.ErrorType.RequestError, ex.getMessage()));
        }
        if (isOfType(ex, HttpMessageNotReadableException.class,
                JsonMappingException.class,
                BigDecimalParseException.class)) {

            ValidationErrorDto dto = new ValidationErrorDto();
            Throwable rootCause = ex;
            while (rootCause.getCause() != null && !rootCause.getCause().equals(rootCause)) {
                rootCause = rootCause.getCause();
            }
            if (rootCause instanceof BigDecimalParseException) {
                dto.addFieldError(((BigDecimalParseException) rootCause).getFieldName(), rootCause.getMessage());
            } else if (rootCause instanceof InvalidFormatException && !((InvalidFormatException) rootCause).getPath()
                    .isEmpty()) {
                dto.addFieldError(((InvalidFormatException) rootCause).getPath().get(0).getFieldName(),
                        messageSource.getMessage("ValidationErrorDto.invalidFormat", null,
                                LocaleContextHolder.getLocale()));
            } else {
                dto.addGlobalError(rootCause.getMessage());
            }
            return new StatusCodeAndResponse(HttpStatus.BAD_REQUEST.value(), dto);
        }
        if (isOfType(ex, HttpMediaTypeNotSupportedException.class)) {
            return new StatusCodeAndResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), new ErrorDto(ErrorDto.ErrorType.UnsupportedMediaType, ex.getLocalizedMessage()));
        }
        if (ex instanceof BindException) {
            return convertToValidationError((BindException) ex);
        }
        if (ex instanceof ValidationException) {
            return convertToValidationError(((ValidationException) ex).getErrors());
        }
        if (ex instanceof ConstraintViolationException) {
            return convertConstraintViolation(((ConstraintViolationException) ex).getConstraintViolations());
        }
        if (isOfType(ex, NotFoundException.class)) {
            if (!StringUtils.isEmpty(ex.getMessage())) {
                return new StatusCodeAndResponse(HttpStatus.NOT_FOUND.value(), new ErrorDto(ErrorDto.ErrorType.NotFound, ex.getMessage()));
            } else {
                // if the message is null then we do not return an errorDto. this is especially important for
                // controllers that do not want to return json but e.g. application/pdf; in that case we must
                // not return json in error cases, but simple null and a 404 response

                // TODO could we detect that from here as well (from the request or response)?
                return new StatusCodeAndResponse(HttpStatus.NOT_FOUND.value(), null);
            }
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return convertToValidationError(((MethodArgumentNotValidException) ex).getBindingResult());
        }

        LOG.error("common exception caught:", ex);
        String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();

        return new StatusCodeAndResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new ErrorDto(ErrorDto.ErrorType.Exception, ex.getClass().getName() + ": " + message, getStackTrace(ex)));
    }

    private StatusCodeAndResponse convertConstraintViolation(Set<ConstraintViolation<?>> constraintViolations) {
        ValidationErrorDto dto = new ValidationErrorDto();
        for (ConstraintViolation constraintViolation : constraintViolations) {
            ObjectError objError = new ObjectError(
                    constraintViolation.getPropertyPath().toString(),
                    constraintViolation.getMessage());

            String localizedErrorMessage = resolveLocalizedErrorMessage(objError);
            dto.addFieldError(constraintViolation.getPropertyPath().toString(), localizedErrorMessage);
        }
        return new StatusCodeAndResponse(HttpStatus.BAD_REQUEST.value(), dto);
    }

    private StatusCodeAndResponse convertToValidationError(Errors errors) {
        ValidationErrorDto dto = new ValidationErrorDto();

        for (FieldError fieldError : errors.getFieldErrors()) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            dto.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        for (ObjectError objectError : errors.getGlobalErrors()) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(objectError);
            dto.addGlobalError(localizedErrorMessage);
        }

        return new StatusCodeAndResponse(HttpStatus.BAD_REQUEST.value(), dto);
    }

    private String resolveLocalizedErrorMessage(ObjectError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(fieldError, currentLocale);
    }

    private static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        // We replace all existing \r to have the same kind of result on windows and on linux.
        return result.toString().replace("\r", "");
    }

    private boolean isOfType(Exception ex, Class<?>... exceptionClass) {
        for (Class<?> clazz : exceptionClass) {
            if (clazz.equals(ex.getClass())) {
                return true;
            }
        }
        return false;
    }
}
