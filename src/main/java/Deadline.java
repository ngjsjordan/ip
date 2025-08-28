import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws MarquessException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw by.isEmpty()
                    ? new InsufficientParametersException("deadline required")
                    : new InvalidParameterException(String.format("%s; datetime required", by));
        }
    }

    public Deadline(boolean isDone, String description, String by) throws MarquessException {
        super(isDone, description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw by.isEmpty()
                    ? new InsufficientParametersException("deadline required")
                    : new InvalidParameterException(String.format("%s; datetime required", by));
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String exportTask() {
        return String.format("D,%s,%s", this.by, super.exportTask());
    }
}
