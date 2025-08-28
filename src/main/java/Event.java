import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) throws MarquessException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw start.isEmpty() || end.isEmpty()
                    ? new InsufficientParametersException("start and end required")
                    : new InvalidParameterException(String.format("%s or %s; datetime required", start, end));
        }
    }

    public Event(boolean isDone, String description, String start, String end) throws MarquessException {
        super(isDone, description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw start.isEmpty() || end.isEmpty()
                    ? new InsufficientParametersException("start and end required")
                    : new InvalidParameterException(String.format("%s or %s; datetime required", start, end));
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(), this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")), this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String exportTask() {
        return String.format("E,%s,%s,%s", this.start, this.end, super.exportTask());
    }
}