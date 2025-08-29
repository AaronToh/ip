package ming.exception;

public class MingException extends Exception {
    private final String message;

    public MingException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ming.exception.MingException: " + message;
    }

    public String getMessage() {
        return message;
    }
}
