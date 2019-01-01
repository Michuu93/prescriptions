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
    editMode: boolean;
    editModeSubscription: Subscription;

    constructor(private patientsService: PatientsService) {
    }

    ngOnInit() {
        this.editModeSubscription = this.patientsService.editMode.subscribe(
            (editMode: boolean) => {
                if (editMode) {
                    this.selectedIndex = 1;
                }
                this.editMode = editMode;
            }
        );
    }

    ngOnDestroy(): void {
        this.editModeSubscription.unsubscribe();
    }
}