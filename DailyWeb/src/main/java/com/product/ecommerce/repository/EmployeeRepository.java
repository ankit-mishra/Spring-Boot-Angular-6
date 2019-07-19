package com.product.ecommerce.repository;

import com.product.ecommerce.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
