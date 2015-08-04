package cc.catalysts.angular.spring.validation;

/**
 * @author Klaus Lehner
 */
public interface ValidationService {

    /**
     * Validates the given object with the {@link org.springframework.validation.Validator} and throws a
     * {@link ValidationException} if there are errors.
     *
     * @param object any object to validate
     * @throws ValidationException if the {@link org.springframework.validation.Validator} collects
     *                             {@link org.springframework.validation.Errors}
     */
    void validate(Object object, Class<?>... validationGroups) throws ValidationException;
}
