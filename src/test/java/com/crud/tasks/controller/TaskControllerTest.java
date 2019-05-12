package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1l, "Task 1", "This is task 1"));
        when(taskMapper.mapToTaskDtoList(anyList())).thenCallRealMethod();
        when(dbService.getAllTasks()).thenReturn(tasks);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Task 1")))
                .andExpect(jsonPath("$[0].content", is("This is task 1")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1l, "Task 1", "This is task 1");
        when(dbService.getTaskById(anyLong())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenCallRealMethod();


        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task 1")))
                .andExpect(jsonPath("$.content", is("This is task 1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        doNothing().when(dbService).deleteTaskById(anyLong());

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1"))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTaskById(1L);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1l, "Task 1", "This is task 1");

        when(dbService.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenCallRealMethod();
        when(taskMapper.mapToTaskDto(any())).thenCallRealMethod();

        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task 1")))
                .andExpect(jsonPath("$.content", is("This is task 1")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(null, "Task 1", "This is task 1");

        when(dbService.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenCallRealMethod();
        when(taskMapper.mapToTaskDto(any())).thenCallRealMethod();

        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(dbService, times(1)).saveTask(any(Task.class));
    }
}