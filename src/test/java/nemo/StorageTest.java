package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nemo.task.Deadline;
import nemo.task.Event;
import nemo.task.Task;
import nemo.task.ToDo;

public class StorageTest {

    @Test
    public void testParseTask() throws NemoException {
        Storage storage = new Storage("tasks.txt");

        // Test parsing a ToDo task
        String todoLine = "[T][ ] Buy groceries";
        Task todo = storage.parseTask(todoLine);
        assertTrue(todo instanceof ToDo, "Parsed task should be an instance of ToDo");
        assertEquals("Buy groceries", todo.getDescription(),
                "Task description should match");

        // Test parsing a Deadline task
        String deadlineLine = "[D][ ] Submit mcm report (by: Oct 22 2025)";
        Task deadline = storage.parseTask(deadlineLine);
        assertTrue(deadline instanceof Deadline, "Parsed task should be an instance of Deadline");
        assertEquals("Submit mcm report", deadline.getDescription(),
                "Task description should match");

        // Test parsing an Event task
        String eventLine = "[E][ ] Team meeting (from: Oct 25 2023 to: Oct 26 2023)";
        Task event = storage.parseTask(eventLine);
        assertTrue(event instanceof Event, "Parsed task should be an instance of Event");
        assertEquals("Team meeting", event.getDescription(),
                "Task description should match");
    }
}