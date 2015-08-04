package cc.catalysts.angular.spring.validation.impl;

import cc.catalysts.angular.spring.validation.ValidationException;
import cc.catalysts.angular.spring.validation.ValidationService;
import cc.catalysts.angular.spring.validation.constraints.impl.DelegatingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.SmartValidator;

import java.util.Objects;

/**
 * <p>
 * This service must be used by any other service before it saves entity objects with a repository. Basically,
 * the hibernate validator would do entity validation implicitely, but Hibernate throws
 * {@link javax.validation.ConstraintViolationException}s instead of
 * {@link org.springframework.validation.BindException} and if this is the case, we cannot translate the error codes to
 * translated messages from our resource bundles.
 * </p>
 * <p>
 * Secondly, the hibernate validator is not triggered immediately after calling repository.save() but on flush.
 * Therefore, it is better to do validation explicitely and not waiting for a hibernate flush. The disadvantage of that
 * approach is, that validation is done twice.
 * </p>
 *
 * @author Klaus Lehner, Catalysts GmbH
 */
@Service
public class ValidationServiceImpl implements ApplicationListener<ContextRefreshedEvent>, ValidationService {
    private final SmartValidator validator;

    @Autowired
    public ValidationServiceImpl(SmartValidator validator) {
        this.validator = validator;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DelegatingValidator.setApplicationContext(event.getApplicationContext());
    }

    /**
     * Validates the given object with the {@link org.springframework.validation.Validator} and throws a
     * {@link ValidationException} if there are errors.
     *
     * @param object any object to validate
     * @throws ValidationException if the {@link org.springframework.validation.Validator} collects
     *                             {@link org.springframework.validation.Errors}
     */
    @Override
    public void validate(Object object, Class<?>... validationGroups) throws ValidationException {
        Objects.requireNonNull(object, "you cannot validate 'null'");
        BindException exception = new BindException(object, object.toString());

        if (validator != null) {
            // ask the SmartValidator (which supports the JSR303 annotation)
            validator.validate(object, exception, validationGroups);
        }

        if (exception.hasErrors()) {
            throw new ValidationException(exception);
        }
    }
}
