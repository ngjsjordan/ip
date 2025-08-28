import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(0);
    }

    @Override
    public String toString() {
        return String.join("",
            Stream.iterate(0, i -> i + 1)
                .limit(taskList.size())
                .map(i -> String.format("%d. %s%n", i + 1,taskList.get(i).toString()))
                .toList());
    }

    public String getTaskDisplay() {
        return String.format("Here are the tasks in your list:%n%s", this);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @return Output message
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return String.format("Got it. I've added this task:%n%s%nNow you have %d task(s) in the list.%n", task, this.taskList.size());
    }

    /**
     * Deletes task at given index.
     *
     * @param i Index of task to be marked.
     * @throws TaskNotFoundException Index of task does not exist.
     */
    public String deleteTask(int i) throws TaskNotFoundException {
        if (i < 0 || i >= this.taskList.size()) {
            throw new TaskNotFoundException(String.format("task %d; only %d tasks added", i + 1, this.taskList.size()));
        }
        Task removed = this.taskList.remove(i);
        return String.format("Noted. I've removed this task:%n%s%nNow you have %d task(s) in the list.%n", removed, this.taskList.size());
    }

    /**
     * Marks task at given index as completed.
     *
     * @param i Index of task to be marked.
     * @throws TaskNotFoundException Index of task does not exist.
     */
    public String markTask(int i) throws TaskNotFoundException {
        if (i < 0 || i >= this.taskList.size()) {
            throw new TaskNotFoundException(String.format("task %d; only %d tasks added", i + 1, this.taskList.size()));
        }
        this.taskList.get(i).mark();
        return String.format("Nice! I've marked this task as done:%n%s%n", this.taskList.get(i));
    }

    /**
     * Marks task at given index as incomplete.
     *
     * @param i Index of task to be marked.
     * @throws TaskNotFoundException Index of task does not exist.
     */
    public String unmarkTask(int i) throws TaskNotFoundException {
        if (i < 0 || i >= this.taskList.size()) {
            throw new TaskNotFoundException(String.format("task %d; only %d tasks added", i + 1, this.taskList.size()));
        }
        this.taskList.get(i).unmark();
        return String.format("OK, I've marked this task as not done yet:%n%s%n", this.taskList.get(i));
    }

    public String exportTasks() {
        return taskList.stream()
                .map(Task::exportTask)
                .reduce("",(acc, t) -> acc + t + "\n");
    }
}
