import {Observable} from "rxjs";
import {DrugPage} from "./model/drug-page.model";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class DrugsService {
    constructor(private http: HttpClient) {
    }

    getDrugPage(page: number, size: number): Observable<DrugPage> {
        let url = "/api/drugs/?page=" + page + "&size=" + size;
        return this.http.get<DrugPage>(url);
    }
}