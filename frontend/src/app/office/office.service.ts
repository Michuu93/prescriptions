import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Office} from "./office.model";
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class OfficeService {
    constructor(private http: HttpClient) {
    }

    getOfficeData(): Observable<Office> {
        return this.http.get<Office>('/api/office/');
    }

    saveOfficeData(data: Office): Observable<Office> {
        return this.http.post<Office>('/api/office/', data);
    }
}