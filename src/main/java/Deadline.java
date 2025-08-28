import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws InsufficientParametersException, DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(boolean isDone, String description, String by) throws InsufficientParametersException, DateTimeParseException {
        super(isDone, description);
        this.by = LocalDate.parse(by);
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
