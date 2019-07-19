package com.product.ecommerce.service;

import com.product.ecommerce.model.EmployeeTask;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface EmployeeTaskService {

    EmployeeTask create(@NotNull(message = "The products for order cannot be null.") @Valid EmployeeTask employeeTask);
}
