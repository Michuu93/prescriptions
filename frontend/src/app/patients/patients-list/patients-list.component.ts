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

    pageIndex: number = 0;
    pageSize: number = 15;
    pageSizeOptions: number[] = [5, 10, 15, 20, 50];
    resultsLength: number;
    isLoadingResults = false;

    constructor(private patientsService: PatientsService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.getPatientsPage(this.pageIndex, this.pageSize);
        this.addPatientSubscription = this.patientsService.patientSaved.subscribe(() => {
            this.getPatientsPage(this.pageIndex, this.pageSize);
        });
    }

    getPatientsPage(pageIndex, pageSize) {
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