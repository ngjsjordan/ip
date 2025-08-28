public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) throws InsufficientParametersException {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(boolean isDone, String description, String start, String end) throws InsufficientParametersException {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(), this.start, this.end);
    }

    @Override
    public String exportTask() {
        return String.format("E,%s,%s,%s", this.start, this.end, super.exportTask());
    }
}