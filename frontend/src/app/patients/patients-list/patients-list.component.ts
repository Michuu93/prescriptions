import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material";
import {PatientsService} from "../patients.service";
import {Patient} from "../model/patient.model";

@Component({
    selector: 'app-patients-list',
    templateUrl: './patients-list.component.html',
    styleUrls: ['./patients-list.component.scss']
})
export class PatientsListComponent implements OnInit {
    displayedColumns: string[] = ['id', 'firstName', 'lastName'];
    dataSource = new MatTableDataSource<Patient>();

    pageIndex: number;
    pageSize: number;
    pageSizeOptions: number[] = [5, 10, 20, 50];
    resultsLength: number;
    isLoadingResults = false;

    constructor(private patientsService: PatientsService) {
    }

    ngOnInit() {
        this.firstLoadDrugPage();
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
                    console.log("Error when getting patients list: " + JSON.stringify(error));
                })
            .add(() => {
                this.isLoadingResults = false;
            });
    }
}