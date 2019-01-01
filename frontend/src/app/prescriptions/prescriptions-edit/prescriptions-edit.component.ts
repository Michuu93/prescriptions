import {Component, OnDestroy, OnInit} from '@angular/core';
import {MatDialog, MatDialogConfig, MatDialogRef} from "@angular/material";
import {PatientsListComponent} from "../../patients/patients-list/patients-list.component";
import {DrugsListComponent} from "../../drugs/drugs-list/drugs-list.component";
import {PatientsService} from "../../patients/patients.service";
import {Subscription} from "rxjs";
import {Patient} from "../../patients/model/patient.model";

@Component({
    selector: 'app-prescriptions-edit',
    templateUrl: './prescriptions-edit.component.html',
    styleUrls: ['./prescriptions-edit.component.scss']
})
export class PrescriptionsEditComponent implements OnInit, OnDestroy {
    dialogConfig: MatDialogConfig;
    patientDialog: MatDialogRef<PatientsListComponent, any>;
    patientSubscription: Subscription;
    selectedPatient: Patient;

    constructor(public dialog: MatDialog, private patientsService: PatientsService) {
    }

    ngOnInit() {
        this.dialogConfig = new MatDialogConfig();
        this.dialogConfig.autoFocus = true;
        this.dialogConfig.height = '90vh';
        this.dialogConfig.width = '100vw';

        this.patientSubscription = this.patientsService.patientEdited.subscribe(
            patient => {
                this.selectedPatient = patient;
                this.patientDialog.close();
            }
        );
    }

    openPatients() {
        this.patientDialog = this.dialog.open(PatientsListComponent, this.dialogConfig);
    }

    openDrugs() {
        this.dialog.open(DrugsListComponent, this.dialogConfig);
    }

    ngOnDestroy(): void {
        this.patientSubscription.unsubscribe();
    }
}