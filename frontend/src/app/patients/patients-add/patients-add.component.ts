import {Component, Inject, LOCALE_ID, OnInit} from '@angular/core';
import {IdType, Patient} from "../model/patient.model";
import {formatDate} from "@angular/common";
import {PatientsService} from "../patients.service";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-patients-add',
    templateUrl: './patients-add.component.html',
    styleUrls: ['./patients-add.component.scss']
})
export class PatientsAddComponent implements OnInit {
    patient: Patient = new Patient();
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
        this.patient.idType = this.idTypes[0].value;
    }

    addPatient() {
        if (this.datePicker) {
            this.patient.birthdate = formatDate(this.datePicker, 'yyyy-MM-dd', this.locale);
        }
        this.patientsService.savePatient(this.patient)
            .subscribe(
                (response: Patient) => {
                    console.log("Save patient: " + JSON.stringify(response));
                    this.snackBar.open('Patient saved!');
                },
                error => {
                    console.log("Error when saving patient: " + JSON.stringify(error));
                    this.snackBar.open('Error when saving patient!');
                });
    }
}