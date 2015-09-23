package cc.catalysts.angular.spring.client;


import cc.catalysts.angular.spring.validation.dto.ValidationErrorDto;

/**
 * Diese Exception wird auf Clients verwendet, um ein {@link ValidationErrorDto} zu wrappen
 *
 * @author Klaus Lehner
 */
public class ClientValidationException extends RuntimeException {

    private final ValidationErrorDto validationErrorDto;

    public ClientValidationException(ValidationErrorDto validationErrorDto) {
        super("ValidationErrors: \n" + validationErrorDto.toString());
        this.validationErrorDto = validationErrorDto;
    }

    public ValidationErrorDto getValidationErrorDto() {
        return validationErrorDto;
    }
}
