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

    public void printTasks() {
        System.out.printf("Here are the tasks in your list:%n%s", this);
    }

    public void addTask(Task t) {
        this.taskList.add(t);
        System.out.printf("Got it. I've added this task:%n%s%nNow you have %d task(s) in the list.%n", t, this.taskList.size());
    }

    public void addTask(Task t, boolean fromStorage) {
        this.taskList.add(t);
    }

    public void deleteTask(int i) throws TaskNotFoundException {
        if (i < 0 || i >= this.taskList.size()) {
            throw new TaskNotFoundException(String.format("task %d; only %d tasks added", i + 1, this.taskList.size()));
        }
        Task removed = this.taskList.remove(i);
        System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d task(s) in the list.%n", removed, this.taskList.size());
    }

    public void markTask(int i) throws TaskNotFoundException {
        if (i < 0 || i >= this.taskList.size()) {
            throw new TaskNotFoundException(String.format("task %d; only %d tasks added", i + 1, this.taskList.size()));
        }
        this.taskList.get(i).mark();
        System.out.printf("Nice! I've marked this task as done:%n%s%n", this.taskList.get(i));
    }

    public void unmarkTask(int i) throws TaskNotFoundException {
        if (i < 0 || i >= this.taskList.size()) {
            throw new TaskNotFoundException(String.format("task %d; only %d tasks added", i + 1, this.taskList.size()));
        }
        this.taskList.get(i).unmark();
        System.out.printf("OK, I've marked this task as not done yet:%n%s%n", this.taskList.get(i));
    }

    public String exportTasks() {
        return taskList.stream()
                .map(Task::exportTask)
                .reduce("",(acc, t) -> acc + t + "\n");
    }
}
