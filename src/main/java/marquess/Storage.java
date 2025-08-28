package marquess;

import marquess.exception.InvalidParameterException;
import marquess.exception.MarquessException;
import marquess.task.Deadline;
import marquess.task.Event;
import marquess.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the saving and loading of Marquess data from txt storage.
 */
public class Storage {
    private final File file;

    /**
     * Constructor for Storage class using a file path.
     *
     * @param filePath File path of the txt file to be used.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean success = file.getParentFile().mkdirs();
                success = success && file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create storage file: " + e);
            }
        }
    }

    /**
     * Saves a task list to file.
     *
     * @param taskList Task list to be saved.
     */
    public void save(TaskList taskList) {
        String formatted = taskList.exportTasks();
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(formatted);
            fw.close();
        } catch (IOException e) {
            System.err.println("Cannot write tasks to file: " + e);
        }
    }

    /**
     * Loads tasks to a task list.
     *
     * @param taskList Task list to be loaded to.
     */
    public void load(TaskList taskList) {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String formatted = s.nextLine();
                try {
                    String[] taskSplit = formatted.split(",", 3);
                    switch (taskSplit[0]) {
                    case "T":
                        taskList.addTask(new Todo(taskSplit[1].equals("1"),
                                taskSplit[2]));
                        break;
                    case "D":
                        taskSplit = formatted.split(",", 4);
                        taskList.addTask(new Deadline(taskSplit[2].equals("1"),
                                taskSplit[3], taskSplit[1]));
                        break;
                    case "E":
                        taskSplit = formatted.split(",", 5);
                        taskList.addTask(new Event(taskSplit[3].equals("1"),
                                taskSplit[4], taskSplit[1], taskSplit[2]));
                        break;
                    default:
                        throw new InvalidParameterException("Invalid Task");
                    }

                } catch (MarquessException e) {
                    System.err.println("Task cannot be loaded: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find task storage file: " + e);
        }
    }
}
