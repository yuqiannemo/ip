package nemo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import nemo.NemoException;

public class TaskListTest {

    @Test
    public void testAddTask() throws NemoException {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("Buy groceries");
        Task deadline = new Deadline("Submit mcm report", LocalDate.of(2025, 10, 22));

        // Test adding a ToDo task
        taskList.add(todo);
        assertEquals(1, taskList.size(), "Task list size should be 1 after adding a task");
        assertEquals(todo, taskList.get(0), "The added task should be the same as the task in the list");

        // Test adding a Deadline task
        taskList.add(deadline);
        assertEquals(2, taskList.size(), "Task list size should be 2 after adding another task");
        assertEquals(deadline, taskList.get(1), "The added task should be the same as the task in the list");
    }
}