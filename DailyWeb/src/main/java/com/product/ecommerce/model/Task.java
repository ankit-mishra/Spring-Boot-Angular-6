package com.product.ecommerce.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tasks")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="employeeTasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy") private LocalDate dateCreated;

    private String status;

    @OneToMany(mappedBy = "pk.task")
    @Valid
    private List<EmployeeTask> employeeTasks = new ArrayList<>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<EmployeeTask> employeeTasks = getEmployeeTasks();
        for (EmployeeTask et : employeeTasks) {
            sum += et.getTotalHours();
        }

        return sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<EmployeeTask> getEmployeeTasks() {
        return employeeTasks;
    }

    public void setEmployeeTasks(List<EmployeeTask> employeeTasks) {
        this.employeeTasks = employeeTasks;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.employeeTasks.size();
    }
}
