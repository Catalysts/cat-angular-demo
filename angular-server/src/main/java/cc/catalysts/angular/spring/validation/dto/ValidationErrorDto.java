package cc.catalysts.angular.spring.validation.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This object is sent to the client in case of validation errors
 *
 * @author Klaus Lehner, Catalysts GmbH
 */
public class ValidationErrorDto {
    private List<String> globalErrors = new ArrayList<String>();
    private List<FieldErrorDto> fieldErrors = new ArrayList<FieldErrorDto>();

    public void addFieldError(String path, String message) {
        FieldErrorDto error = new FieldErrorDto(path, message);
        if (!fieldErrors.contains(error)) {
            fieldErrors.add(error);
        }
    }

    public void addGlobalError(String message) {
        if (!globalErrors.contains(message)) {
            globalErrors.add(message);
        }
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }

    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
    }

    public void setFieldErrors(List<FieldErrorDto> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (!globalErrors.isEmpty()) {
            builder.append("Global Errors:").append("\n");
            for (String globalError : globalErrors) {
                builder.append(" - ").append(globalError).append("\n");
            }
        }
        if (!fieldErrors.isEmpty()) {
            builder.append("Field Errors:").append("\n");
            for (FieldErrorDto fieldError : fieldErrors) {
                builder.append(" - ").append(fieldError.getField()).append(": ").append(fieldError.getMessage()).append("\n");
            }
        }
        return builder.toString();
    }
}
