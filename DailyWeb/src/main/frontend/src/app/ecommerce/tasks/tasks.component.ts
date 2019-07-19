import {Component, OnInit} from '@angular/core';
import {EmployeeTasks} from "../models/employee-tasks.model";
import {Subscription} from "rxjs/internal/Subscription";
import {EcommerceService} from "../services/EcommerceService";

@Component({
    selector: 'app-tasks',
    templateUrl: './tasks.component.html',
    styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
    tasks: EmployeeTasks;
    total: number;
    paid: boolean;
    sub: Subscription;

    constructor(private ecommerceService: EcommerceService) {
        this.tasks = this.ecommerceService.EmployeeTasks;
    }

    ngOnInit() {
        this.paid = false;
        this.sub = this.ecommerceService.TasksChanged.subscribe(() => {
            this.tasks = this.ecommerceService.EmployeeTasks;
        });
        this.loadTotal();
    }

    pay() {
        this.paid = true;
        this.ecommerceService.saveTask(this.tasks).subscribe();
    }

    loadTotal() {
        this.sub = this.ecommerceService.TotalChanged.subscribe(() => {
            this.total = this.ecommerceService.Total;
        });
    }
}
