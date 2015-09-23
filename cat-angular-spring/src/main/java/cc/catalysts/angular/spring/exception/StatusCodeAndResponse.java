package cc.catalysts.angular.spring.exception;

/**
 * @author Klaus Lehner
 */
public class StatusCodeAndResponse {
    private final int statusCode;
    private final Object response;

    public StatusCodeAndResponse(int statusCode, Object response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getResponse() {
        return response;
    }
}
