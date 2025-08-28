package marquess;

import marquess.command.AddCommand;
import marquess.command.Command;
import marquess.exception.MarquessException;
import marquess.task.Deadline;
import marquess.task.Task;
import marquess.task.Todo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parser_addTodoBase_success() throws Exception {
        Field taskField = AddCommand.class.getDeclaredField("task");
        taskField.setAccessible(true);
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);

        Parser parser = new Parser();
        Command c = parser.parse("todo test");
        assertInstanceOf(AddCommand.class, c);
        assertInstanceOf(Todo.class, taskField.get(c));
        assertEquals("test", descriptionField.get(taskField.get(c)));
    }

    @Test
    public void parser_addTodoInsufficientParameters_exceptionThrown() throws Exception {
        Parser parser = new Parser();
        try {
            Command c = parser.parse("todo");
            fail();
        } catch (MarquessException e) {
            assertEquals("The command has insufficient parameters: task requires - description",
                    e.getMessage());
        }
    }

    @Test
    public void parser_addDeadlineBase_success() throws Exception {
        Field taskField = AddCommand.class.getDeclaredField("task");
        taskField.setAccessible(true);
        Field descriptionField = Task.class.getDeclaredField("description");
        descriptionField.setAccessible(true);
        Field byField = Deadline.class.getDeclaredField("by");
        byField.setAccessible(true);

        Parser parser = new Parser();
        Command c = parser.parse("deadline test /by 2025-01-01");
        assertInstanceOf(AddCommand.class, c);
        assertInstanceOf(Deadline.class, taskField.get(c));
        assertEquals("test", descriptionField.get(taskField.get(c)));
        assertEquals("2025-01-01", byField.get(taskField.get(c)).toString());
    }
}
