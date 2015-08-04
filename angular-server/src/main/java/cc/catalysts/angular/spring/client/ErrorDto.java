package cc.catalysts.angular.spring.client;

/**
 * <p>Einfaches DTO zum Wrappen einer Fehlermeldung am Server.</p>
 * <p><b>WICHTIG:</b> die Feldnamen hier dürfen aus historischen Gründen nicht geändert werden, da z.B. cat-angular
 * diese Felder verwendet, um Fehlermeldungen am Client anzuzeigen</p>
 *
 * @author Klaus Lehner
 */
public class ErrorDto {

    private ErrorType error;
    private String cause;
    private String stacktrace;

    public ErrorDto(ErrorType error, String cause) {
        this.error = error;
        this.cause = cause;
    }

    public ErrorDto(ErrorType error, String cause, String stacktrace) {
        this.error = error;
        this.cause = cause;
        this.stacktrace = stacktrace;
    }

    public ErrorDto() {
    }

    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType error) {
        this.error = error;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public enum ErrorType {
        IllegalArgument, NotFound, Exception, UnsupportedMediaType, RequestError
    }
}
