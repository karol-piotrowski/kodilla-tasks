package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @Test
    public void getAllTasks() {
        //Given
        Task task1 = new Task(null, "Task 1", "This is task 1");
        Task task2 = new Task(null, "Task 2", "This is task 2");
        dbService.saveTask(task1);
        dbService.saveTask(task2);

        //When
        List<Task> savedTasks = dbService.getAllTasks();

        //Then
        assertEquals(2, savedTasks.size());

        //Cleanup
        dbService.deleteTaskById(task1.getId());
        dbService.deleteTaskById(task2.getId());
    }

    @Test
    public void getTaskById() {
        //Given
        Task task1 = new Task(null, "Task 1", "This is task 1");
        dbService.saveTask(task1);
        Long savedTaskId = task1.getId();

        //When
        Task savedTask = dbService.getTaskById(savedTaskId);

        //Then
        assertEquals(savedTaskId, savedTask.getId());

        //Cleanup
        dbService.deleteTaskById(task1.getId());
    }

    @Test
    public void saveTask() {
        //Given
        Task task1 = new Task(null, "Task 1", "This is task 1");
        Task task2 = new Task(null, "Task 2", "This is task 2");

        //When
        dbService.saveTask(task1);
        dbService.saveTask(task2);

        //Then
        assertEquals(2, dbService.getAllTasks().size());

        //Cleanup
        dbService.deleteTaskById(task1.getId());
        dbService.deleteTaskById(task2.getId());
    }

    @Test
    public void deleteTaskById() {
        //Given
        Task task1 = new Task(null, "Task 1", "This is task 1");
        Task task2 = new Task(null, "Task 2", "This is task 2");
        dbService.saveTask(task1);
        dbService.saveTask(task2);

        //When
        dbService.deleteTaskById(task1.getId());

        //Then
        assertEquals(1, dbService.getAllTasks().size());

        //Cleanup
        dbService.deleteTaskById(task2.getId());
    }
}