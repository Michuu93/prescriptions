import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {RootComponent} from './root/root.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
    MAT_SNACK_BAR_DEFAULT_OPTIONS,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule, MatInputModule, MatMenuModule,
    MatPaginatorModule, MatProgressSpinnerModule, MatSnackBarModule, MatTableModule,
    MatToolbarModule
} from "@angular/material";
import {HeaderComponent} from './header/header.component';
import {HttpClientModule} from "@angular/common/http";
import { DrugsListComponent } from './drugs/drugs-list/drugs-list.component';
import { DrugsComponent } from './drugs/drugs.component';
import { PrescriptionsComponent } from './prescriptions/prescriptions.component';
import {RoutingModule} from "./routing/routing.module";
import { OfficeComponent } from './office/office.component';
import { PatientsComponent } from './patients/patients.component';
import {FileUploadModule} from 'ng2-file-upload';
import {FormsModule} from "@angular/forms";

@NgModule({
    declarations: [
        RootComponent,
        HeaderComponent,
        DrugsListComponent,
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
        MatProgressSpinnerModule,
        MatMenuModule,
        MatSnackBarModule,
        FileUploadModule,
        MatInputModule,
        FormsModule
    ],
    providers: [
        {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 3000}}
    ],
    bootstrap: [RootComponent]
})
export class AppModule {
}
