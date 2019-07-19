package com.product.ecommerce;

import com.product.ecommerce.model.Employee;
import com.product.ecommerce.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(EmployeeService productService) {
        return args -> {
            productService.save(new Employee(1L, "TaMED", 300.00, ""));
            productService.save(new Employee(2L, "CGTool", 200.00, ""));
            productService.save(new Employee(3L, "SAT42", 100.00, ""));
            productService.save(new Employee(4L, "FPRIME", 5.00, ""));
            productService.save(new Employee(5L, "NBF CONF", 3.00, ""));
        };
    }
}
