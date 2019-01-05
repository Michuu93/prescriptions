import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";

@Component({
    selector: 'app-prescriptions',
    templateUrl: './prescriptions.component.html',
    styleUrls: ['./prescriptions.component.scss']
})
export class PrescriptionsComponent implements OnInit {
    selectedTab;
    editMode: boolean;
    editModeSubscription: Subscription;

    constructor() {
    }

    ngOnInit() {
    }

}