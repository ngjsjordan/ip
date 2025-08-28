public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws InsufficientParametersException {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) throws InsufficientParametersException {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String exportTask() {
        return String.format("D,%s,%s", this.by, super.exportTask());
    }
}
