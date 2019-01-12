import {Component, Inject, LOCALE_ID, OnDestroy, OnInit} from '@angular/core';
import {
    MatDialog,
    MatDialogConfig,
    MatDialogRef,
    MatSnackBar,
    MatTableDataSource
} from "@angular/material";
import {PatientsListComponent} from "../../patients/patients-list/patients-list.component";
import {DrugsListComponent} from "../../drugs/drugs-list/drugs-list.component";
import {PatientsService} from "../../patients/patients.service";
import {Subscription} from "rxjs";
import {DrugsService} from "../../drugs/drugs.service";
import {Drug} from "../../drugs/model/drug.model";
import {Prescription, PrescriptionType, PrescriptionPermissions} from "../model/prescription-model";
import {PrescriptionsService} from "../prescriptions.service";
import {Refund} from "../../drugs/model/refund.model";
import {PrescriptionPreviewComponent} from "../prescription-preview/prescription-preview.component";
import {formatDate} from "@angular/common";

@Component({
    selector: 'app-prescriptions-edit',
    templateUrl: './prescriptions-edit.component.html',
    styleUrls: ['./prescriptions-edit.component.scss']
})
export class PrescriptionsEditComponent implements OnInit, OnDestroy {
    dialogConfig: MatDialogConfig;
    patientDialog: MatDialogRef<PatientsListComponent, any>;
    drugDialog: MatDialogRef<DrugsListComponent, any>;
    selectPatientSubscription: Subscription;
    selectDrugSubscription: Subscription;
    editedPrescription: Prescription = new Prescription();
    editedPrescriptionSubscription: Subscription;
    editModeSubscription: Subscription;
    editMode: boolean;
    dataSource = new MatTableDataSource<Refund>();
    selectedDrugDetails: Drug;
    prescriptionPermissions: string[];
    prescriptionTypes: string[];
    drugsMap: Map<string, Drug> = new Map();
    datePicker: Date;
    minDateFromDay = new Date();

    constructor(public dialog: MatDialog, private prescriptionsService: PrescriptionsService,
                private patientsService: PatientsService, private drugsService: DrugsService,
                private snackBar: MatSnackBar, @Inject(LOCALE_ID) private locale: string) {
        this.prescriptionPermissions = Object.keys(PrescriptionPermissions).filter(key => !Number(key)).slice(1);
        this.prescriptionTypes = Object.keys(PrescriptionType).filter(key => !Number(key)).slice(1);
    }

    ngOnInit() {
        this.dialogConfig = new MatDialogConfig();
        this.dialogConfig.autoFocus = true;
        this.dialogConfig.height = '90vh';
        this.dialogConfig.width = '100vw';

        this.selectPatientSubscription = this.patientsService.patientEdited.subscribe(
            patient => {
                this.editedPrescription.patient = patient;
                this.patientDialog.close();
            }
        );
        this.selectDrugSubscription = this.drugsService.drugSelected.subscribe(
            drug => {
                this.drugsMap.set(drug.bl7, drug);
                this.drugDialog.close();
            }
        );
        this.editedPrescriptionSubscription = this.prescriptionsService.prescriptionsEdited.subscribe(
            prescription => {
                this.editedPrescription = prescription;
                this.datePicker = prescription.dateFromDay;
                this.drugsMap.clear();
                prescription.drugs.forEach(drug => {
                    this.drugsMap.set(drug.bl7, drug);
                });
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
        this.selectedDrugDetails = drug;
        this.dataSource.data = drug.refunds;
    }

    deleteSelectedDrug(bl7: string) {
        this.drugsMap.delete(bl7);
    }

    savePrescription() {
        if (this.datePicker) {
            this.editedPrescription.dateFromDay = formatDate(this.datePicker, 'yyyy-MM-dd', this.locale);
        }
        this.editedPrescription.drugs = Array.from(this.drugsMap.values());
        this.prescriptionsService.savePrescription(this.editedPrescription)
            .subscribe(
                (response: Prescription) => {
                    console.log("Save prescription: " + JSON.stringify(response));
                    this.prescriptionsService.prescriptionsChange.emit();
                    this.prescriptionsService.prescriptionsEdited.emit(response);
                    this.prescriptionsService.editMode.emit(true);
                    this.snackBar.open('Prescription saved!');
                },
                error => {
                    console.log("Error when saving prescription: " + JSON.stringify(error));
                    this.snackBar.open('Error when saving prescription!');
                });
    }

    printPreview() {
        let dialogRef = this.dialog.open(PrescriptionPreviewComponent);
        let dialogInstance = dialogRef.componentInstance;
        dialogInstance.printedPrescription = this.editedPrescription;
    }

    reset() {
        this.editedPrescription = new Prescription();
        this.drugsMap.clear();
        this.prescriptionsService.editMode.emit(false);
    }

    ngOnDestroy(): void {
        this.selectPatientSubscription.unsubscribe();
        this.selectDrugSubscription.unsubscribe();
        this.editedPrescriptionSubscription.unsubscribe();
        this.editModeSubscription.unsubscribe();
    }
}