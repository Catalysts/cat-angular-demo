package cc.catalysts.angular.spring.web.util;

/**
 * A runtime exception that specifies a big decimal parse exception for a certain field.
 *
 * @author Nikolay Kushin, Catalysts GmbH
 */
public class BigDecimalParseException extends RuntimeException {
    private final String fieldName;

    /**
     * @param message   The message for the parse exception.
     * @param fieldName The name of the field where the parse exception occurred.
     */
    public BigDecimalParseException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    /**
     * @return The name of the field where the parse exception occurred.
     */
    public String getFieldName() {
        return fieldName;
    }
}
