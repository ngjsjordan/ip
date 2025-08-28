public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws MarquessException {
        if (description.isEmpty()) {
            throw new InsufficientParametersException("task requires - description");
        }
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) throws MarquessException {
        this.description = description;
        this.isDone = isDone;
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

    /**
     * Returns a String representing the task in the format for storage.
     *
     * @return Comma-separated string containing data related to the task
     */
    public String exportTask() {
        return String.format("%d,%s", isDone ? 1 : 0, description);
    }
}
