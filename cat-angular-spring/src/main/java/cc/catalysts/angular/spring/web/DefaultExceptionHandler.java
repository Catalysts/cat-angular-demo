package cc.catalysts.angular.spring.web;

import cc.catalysts.angular.spring.exception.ExceptionSerializer;
import cc.catalysts.angular.spring.exception.StatusCodeAndResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * This general {@link org.springframework.web.bind.annotation.ControllerAdvice} handles all exceptions that are
 * thrown on {@link org.springframework.web.bind.annotation.RestController}s.
 * This includes {@link cc.catalysts.angular.spring.validation.ValidationException}, {@link javax.validation.ConstraintViolationException} and many more.
 * </p>
 * All exceptions are converted into readable objects for the client.
 *
 * @author Klaus Lehner, Catalysts GmbH
 */
@ControllerAdvice(annotations = RestController.class)
public class DefaultExceptionHandler {

    private final ExceptionSerializer exceptionSerializer;

    /**
     * Erstellt einen neuen {@link cc.catalysts.angular.spring.web.DefaultExceptionHandler}
     */
    @Autowired
    public DefaultExceptionHandler(ExceptionSerializer exceptionSerializer) {
        this.exceptionSerializer = exceptionSerializer;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception ex, HttpServletResponse response) {
        StatusCodeAndResponse statusAndResponse = exceptionSerializer.getStatusAndResponse(ex);
        response.setStatus(statusAndResponse.getStatusCode());
        return statusAndResponse.getResponse();
    }
}
