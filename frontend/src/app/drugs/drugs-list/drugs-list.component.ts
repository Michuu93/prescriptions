import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Drug} from "../model/drug.model";
import {DrugsService} from "../drugs.service";
import {MatPaginator, MatTableDataSource} from "@angular/material";
import {DrugsPage} from "../model/drugs-page.model";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-drugs-list',
    templateUrl: './drugs-list.component.html',
    styleUrls: ['./drugs-list.component.scss']
})
export class DrugsListComponent implements OnInit, OnDestroy {
    displayedColumns: string[] = ['bl7', 'ean', 'name', 'internationalName', 'price'];
    dataSource = new MatTableDataSource<Drug>();
    drugsChangeSubscription: Subscription;
    @ViewChild('searchValue') searchInput: ElementRef;
    @ViewChild(MatPaginator) paginator: MatPaginator;

    pageIndex: number = 0;
    pageSize: number = 15;
    pageSizeOptions: number[] = [5, 15, 10, 20, 50];
    resultsLength: number;
    isLoadingResults = false;

    constructor(private drugsService: DrugsService) {
    }

    ngOnInit() {
        this.getDrugsPage(this.pageIndex, this.pageSize);
        this.drugsChangeSubscription = this.drugsService.drugsChange.subscribe(() => {
            this.getDrugsPage(this.pageIndex, this.pageSize);
        });
    }

    getDrugsPage(pageIndex: number = 0, pageSize: number = this.paginator.pageSize) {
        this.isLoadingResults = true;
        let searchName = this.searchInput.nativeElement.value.trim();
        this.paginator.pageIndex = pageIndex;
        this.drugsService.getDrugsPage(pageIndex, pageSize, searchName)
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

    drugDetails(drug: Drug) {
        this.drugsService.drugSelected.emit(drug);
        this.drugsService.detailsMode.emit(true);
    }

    ngOnDestroy(): void {
        this.drugsChangeSubscription.unsubscribe();
    }
}