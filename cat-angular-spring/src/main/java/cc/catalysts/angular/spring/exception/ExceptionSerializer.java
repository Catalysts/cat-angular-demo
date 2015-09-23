package cc.catalysts.angular.spring.exception;

/**
 * @author Klaus Lehner
 */
public interface ExceptionSerializer {

    StatusCodeAndResponse getStatusAndResponse(Exception ex);

}
