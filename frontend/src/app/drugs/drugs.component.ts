import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {DrugsService} from "./drugs.service";

@Component({
    selector: 'app-drugs',
    templateUrl: './drugs.component.html',
    styles: []
})
export class DrugsComponent implements OnInit, OnDestroy {
    selectedTab;
    detailsMode: boolean;
    detailsModeSubscription: Subscription;

    constructor(private drugsService: DrugsService) {
    }

    ngOnInit() {
        this.detailsModeSubscription = this.drugsService.detailsMode$.subscribe(
            (detailsMode: boolean) => {
                if (detailsMode) {
                    this.selectedTab = 1;
                }
                this.detailsMode = detailsMode;
            }
        );
    }

    ngOnDestroy(): void {
        this.detailsModeSubscription.unsubscribe();
    }

}