import {Component, OnInit} from '@angular/core';
import {Drug} from "../model/drug.model";
import {DrugsService} from "../drugs.service";
import {MatTableDataSource} from "@angular/material";
import {DrugsPage} from "../model/drugs-page.model";

@Component({
    selector: 'app-drugs-list',
    templateUrl: './drugs-list.component.html',
    styleUrls: ['./drugs-list.component.scss']
})
export class DrugsListComponent implements OnInit {
    displayedColumns: string[] = ['bl7', 'ean', 'name', 'internationalName', 'price'];
    dataSource = new MatTableDataSource<Drug>();

    pageIndex: number;
    pageSize: number;
    pageSizeOptions: number[] = [5, 10, 20, 50];
    resultsLength: number;
    isLoadingResults = false;

    constructor(private drugsService: DrugsService) {
    }

    ngOnInit() {
        this.firstLoadDrugPage();
    }

    firstLoadDrugPage() {
        this.pageIndex = 0;
        this.pageSize = 10;
        this.loadDrugsPage(this.pageIndex, this.pageSize);
    }

    loadDrugsPage(pageIndex, pageSize) {
        this.isLoadingResults = true;
        this.drugsService.getDrugsPage(pageIndex, pageSize)
            .subscribe(
                (response: DrugsPage) => {
                    this.dataSource.data = response.content;
                    this.resultsLength = response.totalElements;
                },
                error => {
                    console.log("Error when getting drugs list: " + JSON.stringify(error));
                })
            .add(() => {
                this.isLoadingResults = false;
            });
    }
}