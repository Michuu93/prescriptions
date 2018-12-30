import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {PatientsPage} from "./model/patients-page.model";

@Injectable({providedIn: 'root'})
export class PatientsService {
    constructor(private http: HttpClient) {
    }

    getPatientsPage(page: number, size: number): Observable<PatientsPage> {
        return this.http.get<PatientsPage>(`/api/patients/?page=${page}&size=${size}`);
    }
}