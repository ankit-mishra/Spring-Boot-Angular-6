import {EmployeeTask} from "../models/employee-task.model";
import {Subject} from "rxjs/internal/Subject";
import {EmployeeTasks} from "../models/employee-tasks.model";
import {HttpClient} from '@angular/common/http';
import {Injectable} from "@angular/core";

@Injectable()
export class EcommerceService {
    private employyeesUrl = "/api/employees";
    private tasksUrl = "/api/tasks";

    private employeeTask: EmployeeTask;
    private tasks: EmployeeTasks = new EmployeeTasks();

    private employeeTaskSubject = new Subject();
    private tasksSubject = new Subject();
    private totalSubject = new Subject();

    private total: number;

    EmployeeTasksChanged = this.employeeTaskSubject.asObservable();
    TasksChanged = this.tasksSubject.asObservable();
    TotalChanged = this.totalSubject.asObservable();

    constructor(private http: HttpClient) {
    }

    getAllEmployees() {
        console.log(this.http.get(this.employyeesUrl));
        return this.http.get(this.employyeesUrl);
    }

    saveTask(task: EmployeeTasks) {
        return this.http.post(this.tasksUrl, task);
    }

    set SelectedEmployeeTask(value: EmployeeTask) {
        this.employeeTask = value;
        this.employeeTaskSubject.next();
    }

    get SelectedEmployeeTask() {
        return this.employeeTask;
    }

    set EmployeeTasks(value: EmployeeTasks) {
        this.tasks = value;
        this.tasksSubject.next();
    }

    get EmployeeTasks() {
        return this.tasks;
    }

    get Total() {
        return this.total;
    }

    set Total(value: number) {
        this.total = value;
        this.totalSubject.next();
    }
}
