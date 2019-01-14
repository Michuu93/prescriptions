import {Component, OnDestroy, OnInit} from '@angular/core';
import {PatientsService} from "./patients.service";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-patients',
    templateUrl: './patients.component.html',
    styles: []
})
export class PatientsComponent implements OnInit, OnDestroy {
    selectedTab;
    editMode: boolean;
    editModeSubscription: Subscription;

    constructor(private patientsService: PatientsService) {
    }

    ngOnInit() {
        this.editModeSubscription = this.patientsService.editMode$.subscribe(
            (editMode: boolean) => {
                if (editMode) {
                    this.selectedTab = 1;
                }
                this.editMode = editMode;
            }
        );
    }

    ngOnDestroy(): void {
        this.editModeSubscription.unsubscribe();
    }
}