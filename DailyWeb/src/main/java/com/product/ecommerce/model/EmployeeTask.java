package com.product.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EmployeeTask {

    @EmbeddedId
    @JsonIgnore
    private EmployeeTaskPK pk;

    @Column(nullable = false) private Integer quantity;

    public EmployeeTask() {
        super();
    }

    public EmployeeTask(Task task, Employee product, Integer quantity) {
        pk = new EmployeeTaskPK();
        pk.setTask(task);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @Transient
    public Employee getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public Double getTotalHours() {
        return getProduct().getPrice() * getQuantity();
    }

    public EmployeeTaskPK getPk() {
        return pk;
    }

    public void setPk(EmployeeTaskPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EmployeeTask other = (EmployeeTask) obj;
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }

        return true;
    }
}
