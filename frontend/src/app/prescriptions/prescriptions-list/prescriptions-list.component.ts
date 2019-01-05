import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSnackBar, MatTableDataSource} from "@angular/material";
import {Prescription} from "../model/prescription-model";
import {Subscription} from "rxjs";
import {PrescriptionsService} from "../prescriptions.service";
import {Patient} from "../../patients/model/patient.model";

@Component({
    selector: 'app-prescriptions-list',
    templateUrl: './prescriptions-list.component.html',
    styleUrls: ['./prescriptions-list.component.scss']
})
export class PrescriptionsListComponent implements OnInit, OnDestroy {
    displayedColumns: string[] = ['id'];
    dataSource = new MatTableDataSource<Prescription>();
    prescriptionsChangeSubscription: Subscription;
    @ViewChild('searchValue') searchInput: ElementRef;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    pageIndex: number = 0;
    pageSize: number = 15;
    pageSizeOptions: number[] = [5, 10, 15, 20, 50];
    resultsLength: number;
    isLoadingResults = false;

    constructor(private prescriptionsService: PrescriptionsService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.getPrescriptionsPage(this.pageIndex, this.pageSize);
        this.prescriptionsChangeSubscription = this.prescriptionsService.prescriptionsChange.subscribe(() => {
            this.getPrescriptionsPage(this.pageIndex, this.pageSize);
        });
    }

    getPrescriptionsPage(pageIndex: number = 0, pageSize: number = this.paginator.pageSize) {
        this.isLoadingResults = true;
        let patientId = this.searchInput.nativeElement.value.trim();
        this.paginator.pageIndex = pageIndex;
        this.prescriptionsService.getPrescriptionsPage(pageIndex, pageSize, patientId)
            .subscribe(
                response => {
                    this.dataSource.data = response.content;
                    this.resultsLength = response.totalElements;
                },
                error => {
                    if (error.status != 404) {
                        console.log("Error when getting Prescriptions List: " + JSON.stringify(error));
                        this.snackBar.open('Error when getting Prescriptions List!');
                    }
                })
            .add(() => {
                this.isLoadingResults = false;
            });
    }

    editPrescription(patient: Patient) {
        this.prescriptionsService.prescriptionsEdited.emit(patient);
        this.prescriptionsService.editMode.emit(true);
    }

    ngOnDestroy(): void {
        this.prescriptionsChangeSubscription.unsubscribe();
    }

}