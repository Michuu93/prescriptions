import {Component, OnInit} from '@angular/core';
import {Office} from "./office.model";
import {OfficeService} from "./office.service";

@Component({
    selector: 'app-office',
    templateUrl: './office.component.html',
    styleUrls: ['./office.component.scss']
})
export class OfficeComponent implements OnInit {
    officeData: Office = new Office();

    constructor(private officeService: OfficeService) {
    }

    ngOnInit() {
        this.getOfficeData();
    }

    getOfficeData() {
        this.officeService.getOfficeData()
            .subscribe(
                (response: Office) => {
                    this.officeData = response;
                },
                error => {
                    console.log("Error when getting office data: " + JSON.stringify(error));
                });
    }

    saveOfficeData() {
        this.officeService.saveOfficeData(this.officeData)
            .subscribe(
                (response: Office) => {
                    console.log("Save officeData: " + JSON.stringify(response));
                },
                error => {
                    console.log("Error when saving office data: " + JSON.stringify(error));
                });
    }
}