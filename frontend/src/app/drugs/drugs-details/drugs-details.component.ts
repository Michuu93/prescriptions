import {Component, Inject, LOCALE_ID, OnDestroy, OnInit} from '@angular/core';
import {MatSnackBar, MatTableDataSource} from "@angular/material";
import {Subscription} from "rxjs";
import {DrugsService} from "../drugs.service";
import {Drug} from "../model/drug.model";
import {Refund} from "../model/refund.model";

@Component({
    selector: 'app-drugs-details',
    templateUrl: './drugs-details.component.html',
    styleUrls: ['./drugs-details.component.scss']
})
export class DrugsDetailsComponent implements OnInit, OnDestroy {
    // @ViewChild('patientForm') patientForm: NgForm;
    displayedColumns: string[] = ['level', 'description'];
    dataSource = new MatTableDataSource<Refund>();
    selectedDrug: Drug;
    selectedDrugSubscription: Subscription;
    detailsModeSubscription: Subscription;
    detailsMode: boolean;

    constructor(@Inject(LOCALE_ID) private locale: string, private drugsService: DrugsService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.selectedDrugSubscription = this.drugsService.drugSelected.subscribe(
            drug => {
                this.selectedDrug = drug;
                this.dataSource.data = drug.refunds;
                this.detailsMode = true;
            }
        );
        this.detailsModeSubscription = this.drugsService.detailsMode.subscribe(
            (detailsMode: boolean) => {
                this.detailsMode = detailsMode;
            }
        );
    }

    ngOnDestroy(): void {
        this.selectedDrugSubscription.unsubscribe();
        this.detailsModeSubscription.unsubscribe();
    }
}