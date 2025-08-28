package marquess;

import marquess.task.Deadline;
import marquess.task.Task;
import marquess.task.Todo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void taskList_addTaskBase_success() throws Exception {
        Field taskListField = TaskList.class.getDeclaredField("taskList");
        taskListField.setAccessible(true);
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);

        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("test"));
        @SuppressWarnings("unchecked")
        ArrayList<Task> temp = (ArrayList<Task>) taskListField.get(taskList);
        assertEquals(1, temp.size());
        assertEquals("test", descriptionField.get(temp.get(0)));

        taskList.addTask(new Deadline("test2", "2025-01-01"));
        assertEquals(2, temp.size());
        assertEquals("test2", descriptionField.get(temp.get(1)));
    }
}
