package com.product.ecommerce.dto;

import com.product.ecommerce.model.Employee;

public class EmployeeTaskDto {

    private Employee employee;
    private Integer quantity;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
