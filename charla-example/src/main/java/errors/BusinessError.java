package errors;

public class BusinessError {

    private final String message;

    public BusinessError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
