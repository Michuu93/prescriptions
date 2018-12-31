import {Component, OnDestroy, OnInit} from '@angular/core';
import {MatSnackBar, MatTableDataSource} from "@angular/material";
import {PatientsService} from "../patients.service";
import {Patient} from "../model/patient.model";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-patients-list',
    templateUrl: './patients-list.component.html',
    styleUrls: ['./patients-list.component.scss']
})
export class PatientsListComponent implements OnInit, OnDestroy {
    displayedColumns: string[] = ['id', 'firstName', 'lastName'];
    dataSource = new MatTableDataSource<Patient>();
    addPatientSubscription: Subscription;

    pageIndex: number;
    pageSize: number;
    pageSizeOptions: number[] = [5, 10, 20, 50];
    resultsLength: number;
    isLoadingResults = false;

    constructor(private patientsService: PatientsService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.firstLoadDrugPage();
        this.addPatientSubscription = this.patientsService.patientSaved.subscribe(() => {
            this.loadPatientsPage(this.pageIndex, this.pageSize);
        });
    }

    firstLoadDrugPage() {
        this.pageIndex = 0;
        this.pageSize = 10;
        this.loadPatientsPage(this.pageIndex, this.pageSize);
    }

    loadPatientsPage(pageIndex, pageSize) {
        this.isLoadingResults = true;
        this.patientsService.getPatientsPage(pageIndex, pageSize)
            .subscribe(
                response => {
                    this.dataSource.data = response.content;
                    this.resultsLength = response.totalElements;
                },
                error => {
                    if (error.status != 404) {
                        console.log("Error when getting Patients List: " + JSON.stringify(error));
                        this.snackBar.open('Error when getting Patients List!');
                    }
                })
            .add(() => {
                this.isLoadingResults = false;
            });
    }

    editPatient(patient: Patient) {
        this.patientsService.patientEdited.emit(patient);
        this.patientsService.editPatient.emit();
    }

    ngOnDestroy(): void {
        this.addPatientSubscription.unsubscribe();
    }
}