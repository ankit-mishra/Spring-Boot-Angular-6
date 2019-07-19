import {Employee} from "./employee.model";

export class EmployeeTask {
    employee: Employee;
    quantity: number;

    constructor(employee: Employee, quantity: number) {
        this.employee = employee;
        this.quantity = quantity;
    }
}
