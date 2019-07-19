package com.product.ecommerce.service;

import com.product.ecommerce.model.Task;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface TaskService {

    @NotNull Iterable<Task> getAllTasks();

    Task create(@NotNull(message = "The order cannot be null.") @Valid Task task);

    void update(@NotNull(message = "The order cannot be null.") @Valid Task task);
}
