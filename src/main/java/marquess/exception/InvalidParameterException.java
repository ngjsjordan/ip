package marquess.exception;

public class InvalidParameterException extends MarquessException {

    public InvalidParameterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Invalid parameters were provided: %s", super.getMessage());
    }
}
