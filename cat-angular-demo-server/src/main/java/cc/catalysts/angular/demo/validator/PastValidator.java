package cc.catalysts.angular.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Created by agerstmayr on 06.08.2015.
 */
public class PastValidator implements ConstraintValidator<Past, LocalDate> {

    @Override
    public void initialize(Past past) {
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isBefore(LocalDate.now());
    }
}
