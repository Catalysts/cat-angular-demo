package cc.catalysts.angular.spring.validation.constraints;

import javax.validation.ConstraintValidator;

/**
 * Hilfsklasse für Validatoren mit {@link ValidatedBy}
 *
 * @author Klaus Lehner
 */
public abstract class AbstractValidator<T> implements ConstraintValidator<ValidatedBy, T> {
    @Override
    public void initialize(ValidatedBy constraintAnnotation) {
    }
}
