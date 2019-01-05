import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatDialogConfig, MatDialogRef, MatTableDataSource} from "@angular/material";
import {PatientsListComponent} from "../../patients/patients-list/patients-list.component";
import {DrugsListComponent} from "../../drugs/drugs-list/drugs-list.component";
import {PatientsService} from "../../patients/patients.service";
import {Subscription} from "rxjs";
import {Patient} from "../../patients/model/patient.model";
import {DrugsService} from "../../drugs/drugs.service";
import {Drug} from "../../drugs/model/drug.model";
import {Prescription} from "../model/prescription-model";
import {PrescriptionsService} from "../prescriptions.service";
import {NgForm} from "@angular/forms";
import {Refund} from "../../drugs/model/refund.model";

@Component({
    selector: 'app-prescriptions-edit',
    templateUrl: './prescriptions-edit.component.html',
    styleUrls: ['./prescriptions-edit.component.scss']
})
export class PrescriptionsEditComponent implements OnInit, OnDestroy {
    @ViewChild('prescriptionForm') prescriptionForm: NgForm;
    dialogConfig: MatDialogConfig;
    patientDialog: MatDialogRef<PatientsListComponent, any>;
    drugDialog: MatDialogRef<DrugsListComponent, any>;
    selectPatientSubscription: Subscription;
    selectDrugSubscription: Subscription;
    selectedPatient: Patient;
    selectedDrugs = new Map();
    editedPrescription: Prescription = new Prescription();
    editedPrescriptionSubscription: Subscription;
    editModeSubscription: Subscription;
    editMode: boolean;
    dataSource = new MatTableDataSource<Refund>();
    selectedDrug: Drug;

    constructor(public dialog: MatDialog, private prescriptionsService: PrescriptionsService, private patientsService: PatientsService, private drugsService: DrugsService) {
    }

    ngOnInit() {
        this.dialogConfig = new MatDialogConfig();
        this.dialogConfig.autoFocus = true;
        this.dialogConfig.height = '90vh';
        this.dialogConfig.width = '100vw';

        this.selectPatientSubscription = this.patientsService.patientEdited.subscribe(
            patient => {
                this.selectedPatient = patient;
                this.patientDialog.close();
            }
        );
        this.selectDrugSubscription = this.drugsService.drugSelected.subscribe(
            drug => {
                this.selectedDrugs.set(drug.bl7, drug);
                this.drugDialog.close();
            }
        );
        this.editedPrescriptionSubscription = this.prescriptionsService.prescriptionsEdited.subscribe(
            prescription => {
                this.editedPrescription = prescription;
            }
        );
        this.editModeSubscription = this.prescriptionsService.editMode.subscribe(
            (editMode: boolean) => {
                this.editMode = editMode;
            }
        );
    }

    openPatients() {
        this.patientDialog = this.dialog.open(PatientsListComponent, this.dialogConfig);
    }

    openDrugs() {
        this.drugDialog = this.dialog.open(DrugsListComponent, this.dialogConfig);
    }

    setDrugDetails(drug: Drug) {
        this.selectedDrug = drug;
        this.dataSource.data = drug.refunds;

    }

    ngOnDestroy(): void {
        this.selectPatientSubscription.unsubscribe();
        this.selectDrugSubscription.unsubscribe();
        this.editedPrescriptionSubscription.unsubscribe();
        this.editModeSubscription.unsubscribe();
    }
}