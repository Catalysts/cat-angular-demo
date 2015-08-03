package cc.catalysts.angular.spring.exception;

/**
 * This exception is thrown when a certain entity was not found.
 *
 * @author Klaus Lehner
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
    }
}
