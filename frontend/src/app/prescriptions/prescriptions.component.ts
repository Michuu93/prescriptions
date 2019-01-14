import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {PrescriptionsService} from "./prescriptions.service";

@Component({
    selector: 'app-prescriptions',
    templateUrl: './prescriptions.component.html',
    styles: []
})
export class PrescriptionsComponent implements OnInit, OnDestroy {
    selectedTab;
    editMode: boolean;
    editModeSubscription: Subscription;

    constructor(private prescriptionsService: PrescriptionsService) {
    }

    ngOnInit() {
        this.editModeSubscription = this.prescriptionsService.editMode$.subscribe(
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