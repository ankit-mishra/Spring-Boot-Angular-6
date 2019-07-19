package com.product.ecommerce.service;

import com.product.ecommerce.model.Employee;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface EmployeeService {

    @NotNull Iterable<Employee> getAllEmployees();

    Employee getEmployee(@Min(value = 1L, message = "Invalid product ID.") long id);

    Employee save(Employee product);
}
