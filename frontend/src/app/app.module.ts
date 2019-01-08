import {RootComponent} from './root/root.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HeaderComponent} from './header/header.component';
import {DrugsListComponent} from './drugs/drugs-list/drugs-list.component';
import {DrugsComponent} from './drugs/drugs.component';
import {PrescriptionsComponent} from './prescriptions/prescriptions.component';
import {RoutingModule} from "./routing/routing.module";
import {ConfigurationComponent} from './configuration/configuration.component';
import {PatientsComponent} from './patients/patients.component';
import {MaterialModule} from "./material.module";
import {PatientsListComponent} from "./patients/patients-list/patients-list.component";
import {PatientsEditComponent} from "./patients/patients-edit/patients-edit.component";
import {PrescriptionsListComponent} from "./prescriptions/prescriptions-list/prescriptions-list.component";
import {PrescriptionsEditComponent} from "./prescriptions/prescriptions-edit/prescriptions-edit.component";
import {DrugsDetailsComponent} from "./drugs/drugs-details/drugs-details.component";
import {PrescriptionPreviewComponent} from "./prescriptions/prescription-preview/prescription-preview.component";
import {NgxBarcodeModule} from "ngx-barcode";
import {BrowserModule} from "@angular/platform-browser";
import {HttpClientModule} from "@angular/common/http";
import {FileUploadModule} from "ng2-file-upload";
import {FormsModule} from "@angular/forms";
import {MAT_SNACK_BAR_DEFAULT_OPTIONS} from "@angular/material";
import {NgModule} from "@angular/core";

@NgModule({
    declarations: [
        RootComponent,
        HeaderComponent,
        PrescriptionsComponent,
        PrescriptionsListComponent,
        PrescriptionsEditComponent,
        PrescriptionPreviewComponent,
        DrugsComponent,
        DrugsListComponent,
        DrugsDetailsComponent,
        PatientsComponent,
        PatientsListComponent,
        PatientsEditComponent,
        ConfigurationComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        RoutingModule,
        MaterialModule,
        HttpClientModule,
        FileUploadModule,
        FormsModule,
        NgxBarcodeModule
    ],
    entryComponents: [
        PatientsListComponent,
        DrugsListComponent,
        PrescriptionPreviewComponent
    ],
    providers: [
        {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 3000}}
    ],
    bootstrap: [RootComponent]
})
export class AppModule {
}