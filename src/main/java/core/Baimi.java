package core;

import command.Command;
import exception.BaimiException;
import storage.Storage;
import ui.Ui;

/**
 * The main entry point of the Baimi application.
 * <p>
 * This class initializes the application, loads stored tasks, and manages user interactions.
 */
public class Baimi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Baimi instance with a specified storage file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Baimi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BaimiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application, handling user input.
     * <p>
     * The method continuously reads and processes commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage); // Handle execution
                isExit = c.isExit();
            } catch (BaimiException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) { // Catch generic exceptions from execute()
                ui.showError("An unexpected error occurred: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /*
    public class EmptyDescriptionException extends BaimiException {
        public EmptyDescriptionException(String commandType) {
            super("The description of a " + commandType + " cannot be empty. Please provide a valid description.");
        }
    }

    public class InvalidFormatException extends BaimiException {
        public InvalidFormatException(String correctFormat) {
            super("Invalid format. Correct format: " + correctFormat);
        }
    }

    public class TaskIndexOutOfBoundsException extends BaimiException {
        public TaskIndexOutOfBoundsException(int size) {
            super("Task number is out of range. Please enter a valid task number between 1 and " + size + ".");
        }
    }

    public class UnknownCommandException extends BaimiException {
        public UnknownCommandException() {
            super("Unknown command. Please enter a valid command (e.g., todo, deadline, event, list, delete).");
        }
    }
    */

    /**
     * The main method that starts the Baimi application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Baimi("./data/duke.txt").run();
    }
}