package cc.catalysts.angular.spring.client;

/**
 * Diese Exception wird auf Clients verwendet, um ein {@link ErrorDto} zu wrappen
 *
 * @author Klaus Lehner
 */
public class ClientErrorException extends RuntimeException {

    private final ErrorDto errorDto;

    public ClientErrorException(ErrorDto errorDto) {
        this.errorDto = errorDto;
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }
}
