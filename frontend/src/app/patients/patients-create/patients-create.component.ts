import {Component, Inject, LOCALE_ID, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {IdType, Patient} from "../model/patient.model";
import {formatDate} from "@angular/common";
import {PatientsService} from "../patients.service";
import {MatSnackBar} from "@angular/material";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-patients-create',
    templateUrl: './patients-create.component.html',
    styleUrls: ['./patients-create.component.scss']
})
export class PatientsCreateComponent implements OnInit, OnDestroy {
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
        this.editedPatientSubscription = this.patientsService.patientEdited.subscribe(
            patient => {
                this.editedPatient = patient;
                this.datePicker = patient.birthdate;
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
                    this.patientsService.patientSaved.emit();
                    this.snackBar.open('Patient saved!');
                    this.resetForm();
                },
                error => {
                    console.log("Error when saving patient: " + JSON.stringify(error));
                    this.snackBar.open('Error when saving patient!');
                });
    }

    resetForm() {
        this.editedPatient = new Patient();
        this.editedPatient.idType = this.idTypes[0].value;
        this.patientForm.resetForm();
        Object.keys(this.patientForm.controls).forEach((name) => {
            let control = this.patientForm.controls[name];
            control.setErrors(null);
        });
    }

    ngOnDestroy(): void {
        this.editedPatientSubscription.unsubscribe();
    }
}