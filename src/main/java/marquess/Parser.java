package marquess;

import marquess.command.*;
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
    public Command parse(String s) throws MarquessException {
        String[] params = s.split(" ");

        return switch (params[0]) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "todo" -> new AddCommand(new Todo(
                    Arrays.stream(params)
                            .skip(1)
                            .reduce((acc, word) -> acc + " " + word)
                            .orElse("")));
            case "deadline" -> new AddCommand(new Deadline(
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
            case "event" -> new AddCommand(new Event(
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
            case "mark" -> new MarkCommand(getIndex(params), true);
            case "unmark" -> new MarkCommand(getIndex(params), false);
            case "delete" -> new DeleteCommand(getIndex(params));
            default -> new InvalidCommand(params[0]);
        };
    }

    /**
     * Helper function to obtain index of task from parameter list.
     *
     * @param params Array of parameters.
     * @return Index of task to be operated on.
     * @throws MarquessException If index cannot be obtained from parameters.
     */
    public int getIndex(String[] params) throws MarquessException {
        try {
           return Integer.parseInt(params[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientParametersException("required - index");
        } catch (NumberFormatException e) {
            throw new InvalidParameterException(String.format("%s; required integer", params[1]));
        }
    }
}
