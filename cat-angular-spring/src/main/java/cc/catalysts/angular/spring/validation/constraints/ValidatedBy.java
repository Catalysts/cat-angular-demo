package cc.catalysts.angular.spring.validation.constraints;


import cc.catalysts.angular.spring.validation.constraints.impl.DelegatingValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>Diese Annotation kann verwendet werden, um eine bestimmte Klasse oder ein Feld
 * von einem oder mehreren anderen Validator überprüfen zu lassen.</p>
 * <p/>
 * <p>Zumindest ein {@link #jsrValidator()} oder ein {@link #beanName()} muss gesetzt sein. Alle
 * Validatoren werden aufgerufen.</p>
 *
 * @author Klaus Lehner
 */
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = DelegatingValidator.class)
@Documented
public @interface ValidatedBy {

    /**
     * @return Der JSR 303 Validator
     */
    Class<? extends AbstractValidator>[] jsrValidator() default {};

    /**
     * Der Bean-Name im {@link org.springframework.context.ApplicationContext} des Beans, das die
     * Validierung durchführen soll. Das Bean muss {@link ConstraintValidator} implementieren.
     * Es können auch mehrere Beans angegeben werden, dann werden alle Validatoren ausgeführt.
     *
     * @return der oder die Bean-Namen der Validatoren
     */
    String[] beanName() default {};

    String message() default "ValidatedBy";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Definiert mehrere <code>@ValidatedBy</code> Annotationen am selben Element
     *
     * @see ValidatedBy
     */
    @Target({TYPE, METHOD, FIELD})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ValidatedBy[] value();
    }
}