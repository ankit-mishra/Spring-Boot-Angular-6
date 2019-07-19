package com.product.ecommerce.service;

import com.product.ecommerce.model.Task;
import com.product.ecommerce.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task create(Task task) {
        task.setDateCreated(LocalDate.now());

        return this.taskRepository.save(task);
    }

    @Override
    public void update(Task task) {
        this.taskRepository.save(task);
    }
}
