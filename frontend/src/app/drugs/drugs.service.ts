import {Observable, Subject} from "rxjs";
import {DrugsPage} from "./model/drugs-page.model";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Drug} from "./model/drug.model";

@Injectable({providedIn: 'root'})
export class DrugsService {
    private detailsMode = new Subject<boolean>();
    detailsMode$ = this.detailsMode.asObservable();
    private drugSelected = new Subject<Drug>();
    drugSelected$ = this.drugSelected.asObservable();
    private drugsChange = new Subject();
    drugsChange$ = this.drugsChange.asObservable();

    constructor(private http: HttpClient) {
    }

    getDrugsPage(page: number, size: number, name: string): Observable<DrugsPage> {
        if (name) {
            return this.http.get<DrugsPage>(`/api/drugs/search/${name}/?page=${page}&size=${size}`);
        } else {
            return this.http.get<DrugsPage>(`/api/drugs/?page=${page}&size=${size}`);
        }
    }

    setDetailsMode(isEnable: boolean) {
        this.detailsMode.next(isEnable);
    }

    setSelectedDrug(drug: Drug) {
        this.drugSelected.next(drug);
    }

    refreshDrugs() {
        this.drugsChange.next();
    }
}