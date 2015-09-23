package cc.catalysts.angular.spring.client;

/**
 * @author Klaus Lehner
 */
public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
