package com.product.ecommerce.controller;

import com.product.ecommerce.model.Employee;
import com.product.ecommerce.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
