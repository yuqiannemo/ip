package nemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import nemo.task.Deadline;
import nemo.task.Event;
import nemo.task.Task;
import nemo.task.TaskList;
import nemo.task.ToDo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws NemoException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Successfully created task file: " + file.getPath());
                } else {
                    System.out.println("Failed to create task file.");
                }
            } catch (IOException e) {
                throw new NemoException("Error creating task file: " + e.getMessage());
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new NemoException("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    Task parseTask(String line) throws NemoException {
        if (!line.startsWith("[T]") && !line.startsWith("[E]") && !line.startsWith("[D]")) {
            throw new NemoException("Unrecognized task format: " + line);
        }

        int statusStart = line.indexOf("[", 3);
        int statusEnd = line.indexOf("]", statusStart);
        if (statusStart == -1 || statusEnd == -1) {
            throw new NemoException("Invalid task format: " + line);
        }

        String status = line.substring(statusStart + 1, statusEnd);
        boolean isDone = status.equals("X");

        String taskContent = line.substring(statusEnd + 2).trim();

        Task task = null;
        if (line.startsWith("[T]")) {
            task = new ToDo(taskContent);
        } else if (line.startsWith("[E]")) {
            int fromIndex = taskContent.indexOf("(from: ");
            int toIndex = taskContent.indexOf(" to: ");
            int endIndex = taskContent.indexOf(")");
            if (fromIndex != -1 && toIndex != -1 && endIndex != -1) {
                String description = taskContent.substring(0, fromIndex).trim();
                String from = taskContent.substring(fromIndex + 7, toIndex).trim();
                String to = taskContent.substring(toIndex + 4, endIndex).trim();
                LocalDate dateFrom = LocalDate.parse(from, DateTimeFormatter.ofPattern("MMM d yyyy"));
                LocalDate dateTo = LocalDate.parse(to, DateTimeFormatter.ofPattern("MMM d yyyy"));
                task = new Event(description, dateFrom, dateTo);
            }
        } else if (line.startsWith("[D]")) {
            int byIndex = taskContent.indexOf("(by: ");
            int endIndex = taskContent.indexOf(")");
            if (byIndex != -1 && endIndex != -1) {
                String description = taskContent.substring(0, byIndex).trim();
                String by = taskContent.substring(byIndex + 5, endIndex).trim();
                LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy"));
                task = new Deadline(description, date);
            }
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    public void save(TaskList tasks) throws NemoException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toString() + "\n");
            }
        } catch (IOException e) {
            throw new NemoException("Error saving tasks: " + e.getMessage());
        }
    }
}