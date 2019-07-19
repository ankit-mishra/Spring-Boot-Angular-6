package com.product.ecommerce.repository;

import com.product.ecommerce.model.EmployeeTask;
import com.product.ecommerce.model.EmployeeTaskPK;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeTaskRepository extends CrudRepository<EmployeeTask, EmployeeTaskPK> {
}
