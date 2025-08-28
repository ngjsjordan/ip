package marquess.exception;

public class TaskNotFoundException extends MarquessException {

    public TaskNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("The requested task cannot be found: %s", super.getMessage());
    }
}
