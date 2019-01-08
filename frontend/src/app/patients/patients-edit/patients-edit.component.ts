import {Component, Inject, LOCALE_ID, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {IdType, Patient} from "../model/patient.model";
import {formatDate} from "@angular/common";
import {PatientsService} from "../patients.service";
import {MatSnackBar} from "@angular/material";
import {Subscription} from "rxjs";
import {NgForm} from "@angular/forms";

@Component({
    selector: 'app-patients-edit',
    templateUrl: './patients-edit.component.html',
    styleUrls: ['./patients-edit.component.scss']
})
export class PatientsEditComponent implements OnInit, OnDestroy {
    @ViewChild('patientForm') patientForm: NgForm;
    editedPatient: Patient = new Patient();
    editedPatientSubscription: Subscription;
    editModeSubscription: Subscription;
    idTypes: IdType[] = [
        {value: 'PERSONAL_ID', viewValue: 'Personal ID'},
        {value: 'PARENT_ID', viewValue: 'Parent ID'},
        {value: 'PASSPORT_NUMBER', viewValue: 'Passport Number'}
    ];
    maxDate = new Date();
    datePicker: Date;
    editMode: boolean;

    constructor(@Inject(LOCALE_ID) private locale: string, private patientsService: PatientsService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.editedPatientSubscription = this.patientsService.patientEdited.subscribe(
            patient => {
                this.editedPatient = patient;
                this.datePicker = patient.birthdate;
            }
        );
        this.editModeSubscription = this.patientsService.editMode.subscribe(
            (editMode: boolean) => {
                this.editMode = editMode;
            }
        );
        this.editedPatient.idType = this.idTypes[0].value;
    }

    savePatient() {
        if (this.datePicker) {
            this.editedPatient.birthdate = formatDate(this.datePicker, 'yyyy-MM-dd', this.locale);
        }
        this.patientsService.savePatient(this.editedPatient)
            .subscribe(
                (response: Patient) => {
                    console.log("Save patient: " + JSON.stringify(response));
                    this.patientsService.patientsChange.emit();
                    this.snackBar.open('Patient saved!');
                    this.resetForm();
                },
                error => {
                    console.log("Error when saving patient: " + JSON.stringify(error));
                    this.snackBar.open('Error when saving patient!');
                });
    }

    deletePatient() {
        this.patientsService.deletePatient(this.editedPatient)
            .subscribe(
                () => {
                    console.log("Delete patient: " + JSON.stringify(this.editedPatient));
                    this.patientsService.patientsChange.emit();
                    this.snackBar.open('Patient deleted!');
                    this.resetForm();
                },
                error => {
                    console.log("Error when deleting patient: " + JSON.stringify(error));
                    this.snackBar.open('Error when deleting patient!');
                }
            )
    }

    resetForm() {
        this.patientsService.editMode.emit(false);
        this.editedPatient = new Patient();
        this.editedPatient.idType = this.idTypes[0].value;
        this.patientForm.resetForm();
    }

    ngOnDestroy(): void {
        this.editedPatientSubscription.unsubscribe();
        this.editModeSubscription.unsubscribe();
    }
}