import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {EcommerceComponent} from './ecommerce/ecommerce.component';
import {EmployeesComponent} from './ecommerce/employees/employees.component';
import {DailyTrackerComponent} from './ecommerce/daily-tracker/daily-tracker.component';
import {TasksComponent} from './ecommerce/tasks/tasks.component';
import {EcommerceService} from "./ecommerce/services/EcommerceService";

@NgModule({
    declarations: [
        AppComponent,
        EcommerceComponent,
        EmployeesComponent,
        DailyTrackerComponent,
        TasksComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [EcommerceService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
