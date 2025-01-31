package core;

import command.Command;
import exception.BaimiException;
import storage.Storage;
import ui.Ui;

public class Baimi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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


    public static void main(String[] args) {
        new Baimi("./data/duke.txt").run();
    }
}