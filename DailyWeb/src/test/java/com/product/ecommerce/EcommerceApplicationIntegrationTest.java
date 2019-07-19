package com.product.ecommerce;

import com.product.ecommerce.controller.TasksController;
import com.product.ecommerce.controller.EmployeeController;
import com.product.ecommerce.dto.EmployeeTaskDto;
import com.product.ecommerce.model.Task;
import com.product.ecommerce.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EcommerceApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EcommerceApplicationIntegrationTest {

    @Autowired private TestRestTemplate restTemplate;

    @LocalServerPort private int port;

    @Autowired private EmployeeController employeeController;

    @Autowired private TasksController taskController;

    @Test
    public void contextLoads() {
        Assertions
          .assertThat(employeeController)
          .isNotNull();
        Assertions
          .assertThat(taskController)
          .isNotNull();
    }

    @Test
    public void givenGetProductsApiCall_whenProductListRetrieved_thenSizeMatchAndListContainsProductNames() {
        ResponseEntity<Iterable<Employee>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/products", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Employee>>() {
        });
        Iterable<Employee> products = responseEntity.getBody();
        Assertions
          .assertThat(products)
          .hasSize(7);

        assertThat(products, hasItem(hasProperty("name", is("TV Set"))));
        assertThat(products, hasItem(hasProperty("name", is("Game Console"))));
        assertThat(products, hasItem(hasProperty("name", is("Sofa"))));
        assertThat(products, hasItem(hasProperty("name", is("Icecream"))));
        assertThat(products, hasItem(hasProperty("name", is("Beer"))));
        assertThat(products, hasItem(hasProperty("name", is("Phone"))));
        assertThat(products, hasItem(hasProperty("name", is("Watch"))));
    }

    @Test
    public void givenGetOrdersApiCall_whenProductListRetrieved_thenSizeMatchAndListContainsProductNames() {
        ResponseEntity<Iterable<Task>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/tasks", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Task>>() {
        });

        Iterable<Task> orders = responseEntity.getBody();
        Assertions
          .assertThat(orders)
          .hasSize(0);
    }

    @Test
    public void givenPostOrder_whenBodyRequestMatcherJson_thenResponseContainsEqualObjectProperties() {
        final ResponseEntity<Task> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/api/tasks", prepareTaskForm(), Task.class);
        Task order = postResponse.getBody();
        Assertions
          .assertThat(postResponse.getStatusCode())
          .isEqualByComparingTo(HttpStatus.CREATED);

        assertThat(order, hasProperty("status", is("PAID")));
        assertThat(order.getEmployeeTasks(), hasItem(hasProperty("quantity", is(2))));
    }

    private TasksController.TaskForm prepareTaskForm() {
        TasksController.TaskForm taskForm = new TasksController.TaskForm();
        EmployeeTaskDto employeeDto = new EmployeeTaskDto();
        employeeDto.setEmployee(new Employee(1L, "TV Set", 300.00, "http://placehold.it/200x100"));
        employeeDto.setQuantity(2);
        taskForm.setEmployeeTasks(Collections.singletonList(employeeDto));

        return taskForm;
    }
}
