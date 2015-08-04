package cc.catalysts.angular.spring;

import cc.catalysts.angular.spring.exception.ExceptionSerializer;
import cc.catalysts.angular.spring.exception.impl.DefaultExceptionSerializer;
import cc.catalysts.angular.spring.validation.constraints.impl.DelegatingValidator;
import cc.catalysts.angular.spring.validation.impl.ValidationServiceImpl;
import cc.catalysts.angular.spring.web.DefaultExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan
public class CatAngularModule {
    static {
        DelegatingValidator.setBeanValidationEnabled(true);
    }

    @Bean
    SmartValidator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    ExceptionSerializer exceptionSerializer(MessageSource messageSource) {
        return new DefaultExceptionSerializer(messageSource);
    }

    @Bean
    DefaultExceptionHandler defaultExceptionHandler(ExceptionSerializer exceptionSerializer) {
        return new DefaultExceptionHandler(exceptionSerializer);
    }

    @Bean
    ValidationServiceImpl validationService(SmartValidator validator) {
        return new ValidationServiceImpl(validator);
    }



}
