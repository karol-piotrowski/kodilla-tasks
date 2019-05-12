package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task 1", "This is task 1");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(new Long(1), task.getId());
        assertEquals("Task 1", task.getTitle());
        assertEquals("This is task 1", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Task 1", "This is task 1");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(new Long(1), taskDto.getId());
        assertEquals("Task 1", taskDto.getTitle());
        assertEquals("This is task 1", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", "This is task 1"));
        tasks.add(new Task(2L, "Task 2", "This is task 2"));
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertEquals(new Long(1), taskDtos.get(0).getId());
        assertEquals(new Long(2), taskDtos.get(1).getId());
        assertEquals("Task 1", taskDtos.get(0).getTitle());
        assertEquals("Task 2", taskDtos.get(1).getTitle());
        assertEquals("This is task 1", taskDtos.get(0).getContent());
        assertEquals("This is task 2", taskDtos.get(1).getContent());
        assertEquals(2, taskDtos.size());
    }
}