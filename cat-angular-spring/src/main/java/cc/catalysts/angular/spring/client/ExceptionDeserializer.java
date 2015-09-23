package cc.catalysts.angular.spring.client;

/**
 * @author Klaus Lehner
 */
public interface ExceptionDeserializer {

    RuntimeException getClientException(int status, Object response);

}
