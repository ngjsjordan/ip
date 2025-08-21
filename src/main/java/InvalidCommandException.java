public class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("The command does not exist: %s", super.getMessage());
    }
}
