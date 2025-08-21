public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("The requested task cannot be found: %s", super.getMessage());
    }
}
