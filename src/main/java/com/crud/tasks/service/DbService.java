package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(final long taskId) {
        return repository.findOne(taskId);
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTaskById(final long taskId) {
        repository.delete(taskId);
    }
}
