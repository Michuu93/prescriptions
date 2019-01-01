import {Observable} from "rxjs";
import {DrugsPage} from "./model/drugs-page.model";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class DrugsService {
    constructor(private http: HttpClient) {
    }

    getDrugsPage(page: number, size: number, name: string): Observable<DrugsPage> {
        if (name) {
            return this.http.get<DrugsPage>(`/api/drugs/search/${name}/?page=${page}&size=${size}`);
        } else {
            return this.http.get<DrugsPage>(`/api/drugs/?page=${page}&size=${size}`);
        }
    }
}