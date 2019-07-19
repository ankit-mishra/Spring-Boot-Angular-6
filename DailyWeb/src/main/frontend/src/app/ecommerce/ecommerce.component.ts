import {Component, OnInit, ViewChild} from '@angular/core';
import {EmployeesComponent} from "./employees/employees.component";
import {DailyTrackerComponent} from "./daily-tracker/daily-tracker.component";
import {TasksComponent} from "./tasks/tasks.component";

@Component({
    selector: 'app-ecommerce',
    templateUrl: './ecommerce.component.html',
    styleUrls: ['./ecommerce.component.css']
})
export class EcommerceComponent implements OnInit {
    private collapsed = true;
    taskFinished = false;

    @ViewChild('employeesC')
    employeesC: EmployeesComponent;

    @ViewChild('dailyTrackerC')
    dailyTrackerC: DailyTrackerComponent;

    @ViewChild('tasksC')
    tasksC: TasksComponent;

    constructor() {
    }

    ngOnInit() {
    }

    toggleCollapsed(): void {
        this.collapsed = !this.collapsed;
    }

    finishTask(taskFinished: boolean) {
        this.taskFinished = taskFinished;
    }

    reset() {
        this.taskFinished = false;
        this.employeesC.reset();
        this.dailyTrackerC.reset();
        this.tasksC.paid = false;
    }
}
