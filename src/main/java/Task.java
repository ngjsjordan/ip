public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws InsufficientParametersException {
        if (description.isEmpty()) {
            throw new InsufficientParametersException("task requires - description");
        }
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}
