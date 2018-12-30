import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {RootComponent} from './root/root.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MAT_SNACK_BAR_DEFAULT_OPTIONS} from "@angular/material";
import {HeaderComponent} from './header/header.component';
import {HttpClientModule} from "@angular/common/http";
import {DrugsListComponent} from './drugs/drugs-list/drugs-list.component';
import {DrugsComponent} from './drugs/drugs.component';
import {PrescriptionsComponent} from './prescriptions/prescriptions.component';
import {RoutingModule} from "./routing/routing.module";
import {ConfigurationComponent} from './configuration/configuration.component';
import {PatientsComponent} from './patients/patients.component';
import {FileUploadModule} from 'ng2-file-upload';
import {FormsModule} from "@angular/forms";
import {MaterialModule} from "./material.module";
import {PatientsListComponent} from "./patients/patients-list/patients-list.component";

@NgModule({
    declarations: [
        RootComponent,
        HeaderComponent,
        PrescriptionsComponent,
        DrugsComponent,
        DrugsListComponent,
        PatientsComponent,
        PatientsListComponent,
        ConfigurationComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        RoutingModule,
        MaterialModule,
        HttpClientModule,
        FileUploadModule,
        FormsModule
    ],
    providers: [
        {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 3000}}
    ],
    bootstrap: [RootComponent]
})
export class AppModule {
}