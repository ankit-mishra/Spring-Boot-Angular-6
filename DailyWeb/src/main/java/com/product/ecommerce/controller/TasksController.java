package com.product.ecommerce.controller;

import com.product.ecommerce.dto.EmployeeTaskDto;
import com.product.ecommerce.exception.ResourceNotFoundException;
import com.product.ecommerce.model.Task;
import com.product.ecommerce.model.EmployeeTask;
import com.product.ecommerce.model.TaskStatus;
import com.product.ecommerce.service.EmployeeTaskService;
import com.product.ecommerce.service.TaskService;
import com.product.ecommerce.service.EmployeeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    EmployeeService employeeService;
    TaskService taskService;
    EmployeeTaskService employeeTaskService;

    public TasksController(EmployeeService employeeService, TaskService taskService, EmployeeTaskService employeeTaskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
        this.employeeTaskService = employeeTaskService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Task> list() {
        return this.taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskForm form) {
        List<EmployeeTaskDto> employeeTaskDtos = form.getEmployeeTasks();
        validateProductsExistence(employeeTaskDtos);
        Task task = new Task();
        task.setStatus(TaskStatus.DONE.name());
        task = this.taskService.create(task);

        List<EmployeeTask> employeeTasks = new ArrayList<>();
        for (EmployeeTaskDto dto : employeeTaskDtos) {
        	employeeTasks.add(employeeTaskService.create(new EmployeeTask(task, employeeService.getEmployee(dto
              .getEmployee()
              .getId()), dto.getQuantity())));
        }

        task.setEmployeeTasks(employeeTasks);

        this.taskService.update(task);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(task.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(task, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<EmployeeTaskDto> orderProducts) {
        List<EmployeeTaskDto> list = orderProducts
          .stream()
          .filter(op -> Objects.isNull(employeeService.getEmployee(op
            .getEmployee()
            .getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class TaskForm {

        private List<EmployeeTaskDto> employeeTasks;

        public List<EmployeeTaskDto> getEmployeeTasks() {
            return employeeTasks;
        }

        public void setEmployeeTasks(List<EmployeeTaskDto> employeeTasks) {
            this.employeeTasks = employeeTasks;
        }
    }
}
