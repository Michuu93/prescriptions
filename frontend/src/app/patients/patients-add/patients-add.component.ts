import {Component, Inject, LOCALE_ID, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {IdType, Patient} from "../model/patient.model";
import {formatDate} from "@angular/common";
import {PatientsService} from "../patients.service";
import {MatSnackBar} from "@angular/material";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-patients-add',
    templateUrl: './patients-add.component.html',
    styleUrls: ['./patients-add.component.scss']
})
export class PatientsAddComponent implements OnInit, OnDestroy {
    @ViewChild('patientForm') patientForm;
    editedPatient: Patient = new Patient();
    editedPatientSubscription: Subscription;
    idTypes: IdType[] = [
        {value: 'PERSONAL_ID', viewValue: 'Personal ID'},
        {value: 'PARENT_ID', viewValue: 'Parent ID'},
        {value: 'PASSPORT_NUMBER', viewValue: 'Passport Number'}
    ];
    maxDate = new Date();
    datePicker: Date;

    constructor(@Inject(LOCALE_ID) private locale: string, private patientsService: PatientsService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.editedPatientSubscription = this.patientsService.editedPatient.subscribe(
            patient => {
                this.editedPatient = patient;
                this.datePicker = patient.birthdate;
            }
        );
        this.editedPatient.idType = this.idTypes[0].value;
    }

    addPatient() {
        if (this.datePicker) {
            this.editedPatient.birthdate = formatDate(this.datePicker, 'yyyy-MM-dd', this.locale);
        }
        this.patientsService.savePatient(this.editedPatient)
            .subscribe(
                (response: Patient) => {
                    console.log("Save editedPatient: " + JSON.stringify(response));
                    this.patientsService.addPatient.emit();
                    this.snackBar.open('Patient saved!');
                    this.resetForm();
                },
                error => {
                    console.log("Error when saving editedPatient: " + JSON.stringify(error));
                    this.snackBar.open('Error when saving editedPatient!');
                });
    }

    resetForm() {
        this.editedPatient = new Patient();
        this.editedPatient.idType = this.idTypes[0].value;
        this.patientForm.resetForm();
    }

    ngOnDestroy(): void {
        this.editedPatientSubscription.unsubscribe();
    }
}