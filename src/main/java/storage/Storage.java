package storage;

import core.TaskList;
import ui.Ui;
import exception.BaimiException;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private static final String DIRECTORY_PATH = "./data/";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws BaimiException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;

                Task task;
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        task = new Deadline(description, parts[3]);
                        break;
                    case "E":
                        task = new Event(description, parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }

                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BaimiException("Error loading tasks from file.");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws BaimiException {
        try {
            File directory = new File(DIRECTORY_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(formatTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new BaimiException("Error saving tasks.");
        }
    }

    private String formatTask(Task task) {
        String status = task.isDone() ? "1" : "0";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        if (task instanceof Todo) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + status + " | " + task.getDescription() + " | " + ((Deadline) task).getBy().format(formatter);
        } else if (task instanceof Event) {
            return "E | " + status + " | " + task.getDescription() + " | " + ((Event) task).getFrom().format(formatter) + " | " + ((Event) task).getTo().format(formatter);
        }
        return "";
    }
}

