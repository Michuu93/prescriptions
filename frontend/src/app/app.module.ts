import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {RootComponent} from './root/root.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatPaginatorModule, MatProgressSpinnerModule, MatTableModule,
    MatToolbarModule
} from "@angular/material";
import {HeaderComponent} from './header/header.component';
import {HttpClientModule} from "@angular/common/http";
import { DrugListComponent } from './drugs/drug-list/drug-list.component';
import { DrugsComponent } from './drugs/drugs.component';
import { PrescriptionsComponent } from './prescriptions/prescriptions.component';
import {RoutingModule} from "./routing/routing.module";
import { OfficeComponent } from './office/office.component';
import { PatientsComponent } from './patients/patients.component';



@NgModule({
    declarations: [
        RootComponent,
        HeaderComponent,
        DrugListComponent,
        DrugsComponent,
        PrescriptionsComponent,
        OfficeComponent,
        PatientsComponent
    ],
    imports: [
        BrowserModule,
        RoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        MatPaginatorModule,
        MatFormFieldModule,
        HttpClientModule,
        MatTableModule,
        MatProgressSpinnerModule
    ],
    providers: [],
    bootstrap: [RootComponent]
})
export class AppModule {
}
