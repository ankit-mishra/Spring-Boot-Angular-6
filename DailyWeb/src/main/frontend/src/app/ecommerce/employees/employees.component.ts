import {Component, OnInit} from '@angular/core';
import {EmployeeTask} from "../models/employee-task.model";
import {EcommerceService} from "../services/EcommerceService";
import {Subscription} from "rxjs/internal/Subscription";
import {EmployeeTasks} from "../models/employee-tasks.model";
import {Employee} from "../models/employee.model";

@Component({
    selector: 'app-employees',
    templateUrl: './employees.component.html',
    styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {
    employeeTasks: EmployeeTask[] = [];
    employees: Employee[] = [];
    selectedEmployeeTask: EmployeeTask;
    private dailyTasks: EmployeeTasks;
    sub: Subscription;
    employeeSelected: boolean = false;

    constructor(private ecommerceService: EcommerceService) {
    }

    ngOnInit() {
        this.employeeTasks = [];
        this.loadEmployees();
        this.loadTasks();
    }

    addToCart(task: EmployeeTask) {
        this.ecommerceService.SelectedEmployeeTask = task;
        this.selectedEmployeeTask = this.ecommerceService.SelectedEmployeeTask;
        this.employeeSelected = true;
    }

    removeFromCart(employeeTask: EmployeeTask) {
        let index = this.getEmployeeIndex(employeeTask.employee);
        if (index > -1) {
            this.dailyTasks.employeeTasks.splice(
                this.getEmployeeIndex(employeeTask.employee), 1);
        }
        this.ecommerceService.EmployeeTasks = this.dailyTasks;
        this.dailyTasks = this.ecommerceService.EmployeeTasks;
        this.employeeSelected = false;
    }

    getEmployeeIndex(employee: Employee): number {
        return this.ecommerceService.EmployeeTasks.employeeTasks.findIndex(
            value => value.employee === employee);
    }

    isEmployeeSelected(employee: Employee): boolean {
        return this.getEmployeeIndex(employee) > -1;
    }

    loadEmployees() {
        this.ecommerceService.getAllEmployees()
            .subscribe(
                (employees: any[]) => {
                    this.employees = employees;
                    this.employees.forEach(employee => {
                        this.employeeTasks.push(new EmployeeTask(employee, 0));
                    })
                },
                (error) => console.log(error)
            );
    }

    loadTasks() {
        this.sub = this.ecommerceService.TasksChanged.subscribe(() => {
            this.dailyTasks = this.ecommerceService.EmployeeTasks;
        });
    }

    reset() {
        this.employeeTasks = [];
        this.loadEmployees();
        this.ecommerceService.EmployeeTasks.employeeTasks = [];
        this.loadTasks();
        this.employeeSelected = false;
    }
}
