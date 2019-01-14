import {Component, OnInit} from '@angular/core';
import {OfficeData} from "./model/office.model";
import {ConfigurationService} from "./configuration.service";
import {DoctorData} from "./model/doctor.model";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-office',
    templateUrl: './configuration.component.html',
    styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent implements OnInit {
    officeData: OfficeData = new OfficeData();
    doctorData: DoctorData = new DoctorData();

    constructor(private officeService: ConfigurationService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.getOfficeData();
        this.getDoctorData();
    }

    saveOfficeData() {
        this.officeService.saveOfficeData(this.officeData)
            .subscribe(
                (response: OfficeData) => {
                    console.log("Save officeData: " + JSON.stringify(response));
                    this.snackBar.open('Office Data saved!');
                },
                error => {
                    console.log("Error when saving officeData: " + JSON.stringify(error));
                    this.snackBar.open('Error when saving Office Data!');
                });
    }

    saveDoctorData() {
        this.officeService.saveDoctorData(this.doctorData)
            .subscribe(
                (response: DoctorData) => {
                    console.log("Save doctorData: " + JSON.stringify(response));
                    this.snackBar.open('Doctor Data saved!');
                },
                error => {
                    console.log("Error when saving Doctor Data: " + JSON.stringify(error));
                    this.snackBar.open('Error when saving Doctor Data!');
                });
    }

    private getOfficeData() {
        this.officeService.getOfficeData()
            .subscribe(
                (response: OfficeData) => {
                    this.officeData = response;
                },
                error => {
                    if (error.status != 404) {
                        console.log("Error when getting Office Data: " + JSON.stringify(error));
                        this.snackBar.open('Error when getting Office Data!');
                    }
                });
    }

    private getDoctorData() {
        this.officeService.getDoctorData()
            .subscribe(
                (response: DoctorData) => {
                    this.doctorData = response;
                },
                error => {
                    if (error.status != 404) {
                        console.log(error.status + "Error when getting Doctor Data: " + JSON.stringify(error));
                        this.snackBar.open('Error when getting Doctor Data!');
                    }
                });
    }
}