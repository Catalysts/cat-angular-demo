package cc.catalysts.angular.spring.web;

import cc.catalysts.angular.spring.validation.dto.ValidationErrorDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Klaus Lehner
 */
@ControllerAdvice(annotations = RestController.class)
public class JpaExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(JpaExceptionHandler.class);

    private static final String ERROR = "error";
    private static final String CAUSE = "cause";

    private final MessageSource messageSource;

    @Autowired
    public JpaExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    Map<String, Object> handleDataAccessException(DataAccessException ex) throws IOException {
        LOG.error("Data Access Exception caught:", ex);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ERROR, "Data Error");
        map.put(CAUSE, ex.getCause().getMessage());
        return map;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    ValidationErrorDto handleDataIntegrityViolationException(DataIntegrityViolationException ex) throws
            IOException {
        LOG.error("DataIntegrityViolationException caught:", ex);
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            String constraintName = ((org.hibernate.exception.ConstraintViolationException) ex.getCause())
                    .getConstraintName();
            if (StringUtils.isNotBlank(constraintName)) {
                String[] parts = constraintName.split("_");
                if (parts.length == 3 && "UK".equalsIgnoreCase(parts[0])) {
                    ValidationErrorDto dto = new ValidationErrorDto();
                    dto.addFieldError(parts[parts.length - 1],
                            messageSource.getMessage("ValidationErrorDto.notUniqueValue", null,
                                    LocaleContextHolder.getLocale()));

                    return dto;
                }
            }
        }
        ValidationErrorDto dto = new ValidationErrorDto();
        dto.addGlobalError(String.format("Data Integrity Error: %s",
                ex.getCause().getCause().getLocalizedMessage()));
        return dto;
    }

    @ExceptionHandler({ObjectRetrievalFailureException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    Map<String, Object> handleObjectRetrievalException(Exception ex) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ERROR, "Entity Not Found");
        map.put(CAUSE, ex.getMessage());
        return map;
    }

}
