package ghbot;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import ghbot.exception.GhBotException;
import ghbot.executor.ByeExecutor;
import ghbot.executor.Executor;
import ghbot.parser.Parser;
import ghbot.storage.Storage;
import ghbot.task.TaskList;
import ghbot.ui.Ui;

/**
 * GhBot Class.
 * This is the main class for the chatbot.
 */
public class GhBot {
    private Storage storage;
    private TaskList taskLst;
    private Ui ui;

    /**
     * GhBot Constructor.
     * @param filename The name of the file.
     */
    public GhBot(String filename) {
        this.storage = new Storage(filename);
        this.taskLst = new TaskList(storage.getInputFromFile());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        this.ui = new Ui(input);
        try {
            String[] subStr = this.ui.validateInput();
            Executor executor = Parser.parse(subStr);

            if (executor instanceof ByeExecutor) {
                this.storage.writeDataToFile(taskLst.toList());
                return executor.execute();
            }

            if (executor != null) {
                executor.set(this.taskLst);
                return executor.execute();
            }
        } catch (GhBotException | IOException | DateTimeParseException e) {
            return e.getMessage();
        }
        return "";
    }
}
