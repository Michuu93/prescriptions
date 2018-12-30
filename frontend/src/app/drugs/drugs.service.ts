import {Observable} from "rxjs";
import {DrugPage} from "./model/drugs-page.model";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class DrugsService {
    constructor(private http: HttpClient) {
    }

    getDrugPage(page: number, size: number): Observable<DrugPage> {
        return this.http.get<DrugPage>(`/api/drugs/?page=${page}&size=${size}`);
    }
}