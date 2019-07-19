import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {EmployeeTasks} from "../models/employee-tasks.model";
import {EmployeeTask} from "../models/employee-task.model";
import {EcommerceService} from "../services/EcommerceService";
import {Subscription} from "rxjs/internal/Subscription";

@Component({
    selector: 'app-daily-tracker',
    templateUrl: './daily-tracker.component.html',
    styleUrls: ['./daily-tracker.component.css']
})
export class DailyTrackerComponent implements OnInit, OnDestroy {
    orderFinished: boolean;
    tasks: EmployeeTasks;
    total: number;
    sub: Subscription;

    @Output() onOrderFinished: EventEmitter<boolean>;

    constructor(private ecommerceService: EcommerceService) {
        this.total = 0;
        this.orderFinished = false;
        this.onOrderFinished = new EventEmitter<boolean>();
    }

    ngOnInit() {
        this.tasks = new EmployeeTasks();
        this.loadCart();
        this.loadTotal();
    }

    private calculateTotal(employees: EmployeeTask[]): number {
        let sum = 0;
        employees.forEach(value => {
            sum += (value.quantity);
        });
        return sum;
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    finishOrder() {
        this.orderFinished = true;
        this.ecommerceService.Total = this.total;
        this.onOrderFinished.emit(this.orderFinished);
    }

    loadTotal() {
        this.sub = this.ecommerceService.TasksChanged.subscribe(() => {
            this.total = this.calculateTotal(this.tasks.employeeTasks);
        });
    }

    loadCart() {
        this.sub = this.ecommerceService.EmployeeTasksChanged.subscribe(() => {
            let employeeTask = this.ecommerceService.SelectedEmployeeTask;
            if (employeeTask) {
                this.tasks.employeeTasks.push(new EmployeeTask(
                    employeeTask.employee, employeeTask.quantity));
            }
            this.ecommerceService.EmployeeTasks = this.tasks;
            this.tasks = this.ecommerceService.EmployeeTasks;
            this.total = this.calculateTotal(this.tasks.employeeTasks);
        });
    }

    reset() {
        this.orderFinished = false;
        this.tasks = new EmployeeTasks();
        this.tasks.employeeTasks = []
        this.loadTotal();
        this.total = 0;
    }
}
