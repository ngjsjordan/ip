public class InsufficientParametersException extends Exception {

    public InsufficientParametersException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("The command has insufficient parameters: %s", super.getMessage());
    }
}
