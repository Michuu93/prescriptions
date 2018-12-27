import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Drug} from "../model/drug.model";
import {DrugsService} from "../drugs.service";
import {MatTableDataSource} from "@angular/material";

@Component({
    selector: 'app-drug-list',
    templateUrl: './drugs-list.component.html',
    styleUrls: ['./drugs-list.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush,
    providers: [DrugsService]
})
export class DrugsListComponent implements OnInit {
    displayedColumns: string[] = ['bl7', 'ean', 'name', 'internationalName', 'price'];
    dataSource = new MatTableDataSource<Drug>();

    pageIndex = 0;
    pageSize = 10;
    pageSizeOptions: number[] = [5, 10, 20, 50];
    resultsLength: number;
    isLoadingResults = false;

    constructor(private drugsService: DrugsService) {
    }

    ngOnInit() {
        this.getDrugPage(this.pageIndex, this.pageSize);
    }

    getDrugPage(pageIndex, pageSize): void {
        this.isLoadingResults = true;
        this.drugsService.getDrugPage(pageIndex, pageSize)
            .subscribe(
                response => {
                    this.dataSource.data = response.content;
                    this.resultsLength = response.totalElements;
                    this.isLoadingResults = false;
                })
    }
}