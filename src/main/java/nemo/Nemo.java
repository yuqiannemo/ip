package nemo;

import nemo.command.Command;
import nemo.task.TaskList;

import java.util.Scanner;

public class Nemo {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Nemo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NemoException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (NemoException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Nemo("tasks.txt").run();
    }
}
