package cc.catalysts.angular.spring.client.impl;


import cc.catalysts.angular.spring.client.ClientException;
import cc.catalysts.angular.spring.client.ClientValidationException;
import cc.catalysts.angular.spring.client.ExceptionDeserializer;
import cc.catalysts.angular.spring.validation.dto.ValidationErrorDto;

/**
 * @author Klaus Lehner
 */
public class DefaultExceptionDeserializer implements ExceptionDeserializer {
    @Override
    public RuntimeException getClientException(int status, Object response) {
        if (status == 400) {
            return new ClientValidationException((ValidationErrorDto) response);
        }
        // TODO other types
        return new ClientException("");
    }
}
