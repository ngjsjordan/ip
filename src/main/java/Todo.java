public class Todo extends Task {

    public Todo(String description) throws InsufficientParametersException {
        super(description);
    }

    public Todo(boolean isDone, String description) throws InsufficientParametersException {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String exportTask() {
        return "T," + super.exportTask();
    }
}