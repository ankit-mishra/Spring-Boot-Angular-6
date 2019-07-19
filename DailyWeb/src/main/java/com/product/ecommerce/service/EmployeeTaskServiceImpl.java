package com.product.ecommerce.service;

import com.product.ecommerce.model.EmployeeTask;
import com.product.ecommerce.repository.EmployeeTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeTaskServiceImpl implements EmployeeTaskService {

    private EmployeeTaskRepository employeeTaskRepository;

    public EmployeeTaskServiceImpl(EmployeeTaskRepository employeeTaskRepository) {
        this.employeeTaskRepository = employeeTaskRepository;
    }

    @Override
    public EmployeeTask create(EmployeeTask employeeTask) {
        return this.employeeTaskRepository.save(employeeTask);
    }
}
