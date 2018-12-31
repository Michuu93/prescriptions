import {Component, OnDestroy, OnInit} from '@angular/core';
import {PatientsService} from "./patients.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-patients',
    templateUrl: './patients.component.html',
    styleUrls: ['./patients.component.scss']
})
export class PatientsComponent implements OnInit, OnDestroy {
    selectedIndex;
    editPatientSubscription: Subscription;

    constructor(private patientsService: PatientsService) {
    }

    ngOnInit() {
        this.editPatientSubscription = this.patientsService.editPatient.subscribe(
            () => {
                this.selectedIndex = 1;
            }
        );
    }

    ngOnDestroy(): void {
        this.editPatientSubscription.unsubscribe();
    }
}