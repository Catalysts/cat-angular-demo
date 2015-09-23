package cc.catalysts.angular.spring.validation;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * The ValidationException wraps a {@link org.springframework.validation.BindException} object that is filled by the
 * {@link cc.catalysts.angular.spring.validation.ValidationService}. It is caught by the {@link cc.catalysts.angular.spring.web.DefaultExceptionHandler} for
 * further processing on the client.
 *
 * @author Klaus Lehner, Catalysts GmbH
 */
public class ValidationException extends RuntimeException {
    private final Errors errors;

    public ValidationException(BindException exception) {
        super(exception);
        this.errors = exception;
    }

    public Errors getErrors() {
        return errors;
    }
}
