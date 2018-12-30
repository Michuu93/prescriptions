import {Component, OnInit} from '@angular/core';
import {OfficeData} from "./model/office.model";
import {ConfigurationService} from "./configuration.service";
import {DoctorData} from "./model/doctor.model";

@Component({
    selector: 'app-office',
    templateUrl: './configuration.component.html',
    styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent implements OnInit {
    officeData: OfficeData = new OfficeData();
    doctorData: DoctorData = new DoctorData();

    constructor(private officeService: ConfigurationService) {
    }

    ngOnInit() {
        this.getOfficeData();
        this.getDoctorData();
    }

    private getOfficeData() {
        this.officeService.getOfficeData()
            .subscribe(
                (response: OfficeData) => {
                    this.officeData = response;
                },
                error => {
                    console.log("Error when getting officeData: " + JSON.stringify(error));
                });
    }

    saveOfficeData() {
        this.officeService.saveOfficeData(this.officeData)
            .subscribe(
                (response: OfficeData) => {
                    console.log("Save officeData: " + JSON.stringify(response));
                },
                error => {
                    console.log("Error when saving officeData: " + JSON.stringify(error));
                });
    }

    private getDoctorData() {
        this.officeService.getDoctorData()
            .subscribe(
                (response: DoctorData) => {
                    this.doctorData = response;
                },
                error => {
                    console.log("Error when getting doctorData: " + JSON.stringify(error));
                });
    }

    saveDoctorData() {
        this.officeService.saveDoctorData(this.doctorData)
            .subscribe(
                (response: DoctorData) => {
                    console.log("Save doctorData: " + JSON.stringify(response));
                },
                error => {
                    console.log("Error when saving doctorData: " + JSON.stringify(error));
                });
    }
}