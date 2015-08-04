package cc.catalysts.angular.spring.validation.constraints.impl;

import cc.catalysts.angular.spring.validation.constraints.AbstractValidator;
import cc.catalysts.angular.spring.validation.constraints.ValidatedBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Klaus Lehner
 */
public class DelegatingValidator implements ConstraintValidator<ValidatedBy, Object> {

    private final List<ConstraintValidator> delegates = new ArrayList<ConstraintValidator>();
    private static ApplicationContext applicationContext;

    private static boolean beanValidationEnabled;

    private static final Logger LOG = LoggerFactory.getLogger(DelegatingValidator.class);

    public static void setApplicationContext(ApplicationContext applicationContext) {
        DelegatingValidator.applicationContext = applicationContext;
    }

    public static void setBeanValidationEnabled(boolean beanValidationEnabled) {
        DelegatingValidator.beanValidationEnabled = beanValidationEnabled;
    }

    @Override
    public void initialize(ValidatedBy constraintAnnotation) {
        for (Class<? extends AbstractValidator> validator : constraintAnnotation.jsrValidator()) {
            try {
                delegates.add(validator.newInstance());
            } catch (Exception e) {
                throw new IllegalStateException(validator + " cannot be instantiated");
            }
        }
        if (beanValidationEnabled) {
            if (applicationContext == null) {
                throw new IllegalStateException("BeanValidation is enabled but no ApplicationContext is set. Probably validation is performed before ApplicationContext is initialized");
            } else {
                for (String beanName : constraintAnnotation.beanName()) {
                    Object bean = applicationContext.getBean(beanName);
                    if (bean == null) {
                        throw new IllegalStateException("Bean with name " + beanName + " does not exist");
                    }
                    if (bean instanceof ConstraintValidator) {
                        delegates.add((ConstraintValidator) bean);
                    } else {
                        throw new IllegalStateException("Bean " + beanName + " is no ConstraintValidator");
                    }
                }
            }
        } else {
            if (constraintAnnotation.beanName().length > 0) {
                LOG.warn("BeanValidation is disabled. Validators " + Arrays.toString(constraintAnnotation.beanName()) + " will not be used.");
            }
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;
        context.disableDefaultConstraintViolation();
        for (ConstraintValidator delegate : delegates) {
            if (!delegate.isValid(value, context)) {
                valid = false;
            }
        }
        return valid;
    }
}
