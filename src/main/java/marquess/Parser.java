package marquess;

import marquess.command.AddCommand;
import marquess.command.Command;
import marquess.command.DeleteCommand;
import marquess.command.ExitCommand;
import marquess.command.FindCommand;
import marquess.command.InvalidCommand;
import marquess.command.ListCommand;
import marquess.command.MarkCommand;
import marquess.exception.InsufficientParametersException;
import marquess.exception.InvalidParameterException;
import marquess.exception.MarquessException;
import marquess.task.Deadline;
import marquess.task.Event;
import marquess.task.Todo;

import java.util.Arrays;

/**
 * Parses input strings into commands.
 */
class Parser {
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    /**
     * Method for parsing user input strings into Command objects.
     * @param s String to be parsed.
     * @return Command that was parsed from the input string.
     * @throws MarquessException If command was entered incorrectly.
     */
    public Command parse(String s) throws MarquessException {
        String[] params = s.split(" ");

        if (params.length == 0) {
            return new InvalidCommand("");
        }

        return switch (params[0]) {
            case COMMAND_BYE -> new ExitCommand();
            case COMMAND_LIST -> new ListCommand();
            case COMMAND_TODO -> new AddCommand(new Todo(
                    Arrays.stream(params)
                            .skip(1)
                            .reduce((acc, word) -> acc + " " + word)
                            .orElse("")));
            case COMMAND_DEADLINE -> new AddCommand(new Deadline(
                    Arrays.stream(params)
                            .skip(1)
                            .takeWhile(word -> !word.equals("/by"))
                            .reduce((acc, word) -> acc + " " + word)
                            .orElse(""),
                    Arrays.stream(params)
                            .dropWhile(word -> !word.equals("/by"))
                            .skip(1)
                            .reduce((acc, word) -> acc + " " + word)
                            .orElse("")));
            case COMMAND_EVENT -> new AddCommand(new Event(
                    Arrays.stream(params)
                            .skip(1)
                            .takeWhile(word -> !(word.equals("/from") || word.equals("/to")))
                            .reduce((acc, word) -> acc + " " + word)
                            .orElse(""),
                    Arrays.stream(params)
                            .dropWhile(word -> !word.equals("/from"))
                            .skip(1)
                            .takeWhile(word -> !word.equals("/to"))
                            .reduce((acc, word) -> acc + " " + word)
                            .orElse(""),
                    Arrays.stream(params)
                            .dropWhile(word -> !word.equals("/to"))
                            .skip(1)
                            .reduce((acc, word) -> acc + " " + word)
                            .orElse("")));
            case COMMAND_MARK -> new MarkCommand(true, getIndex(params));
            case COMMAND_UNMARK -> new MarkCommand(false, getIndex(params));
            case COMMAND_DELETE -> new DeleteCommand(getIndex(params));
            case COMMAND_FIND -> new FindCommand(getSearchString(s));
            default -> new InvalidCommand(params[0]);
        };
    }

    /**
     * Helper function to obtain indices of tasks from parameter list.
     * Parses all parameters other than the first one to obtain an array of
     * integer indices in reverse order (for bulk deletions).
     *
     * @param params Array of parameters.
     * @return Indices of tasks to be operated on, in reverse order.
     * @throws MarquessException If index cannot be obtained from parameters.
     */
    public Integer[] getIndex(String[] params) throws MarquessException {
        try {
            return Arrays.stream(params)
                    .skip(1)
                    .map(p -> Integer.parseInt(p) - 1)
                    .sorted((x, y) -> Integer.compare(y, x))
                    .toArray(Integer[]::new);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientParametersException("required - index");
        } catch (NumberFormatException e) {
            throw new InvalidParameterException(String.format("%s; required integer", params[1]));
        }
    }

    /**
     * Helper function to obtain search string from full input string.
     *
     * @param s Input string.
     * @return Search string.
     * @throws MarquessException If no parameters are provided.
     */
    public String getSearchString(String s) throws MarquessException {
        try {
            return s.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientParametersException("required - search string");
        }
    }
}
