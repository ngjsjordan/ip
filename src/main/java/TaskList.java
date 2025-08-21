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
        System.out.printf("added: %s%n", t);
    }

    public void markTask(int i) {
        this.taskList.get(i).mark();
        System.out.printf("Nice! I've marked this task as done:%n%s%n", this.taskList.get(i));
    }

    public void unmarkTask(int i) {
        this.taskList.get(i).unmark();
        System.out.printf("OK, I've marked this task as not done yet:%n%s%n", this.taskList.get(i));
    }

}
