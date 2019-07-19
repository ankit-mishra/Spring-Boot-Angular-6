package com.product.ecommerce.repository;

import com.product.ecommerce.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
