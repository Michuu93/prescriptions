import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {OfficeData} from "./office.model";
import {Observable} from "rxjs";
import {DoctorData} from "./doctor.model";

@Injectable({providedIn: 'root'})
export class ConfigurationService {
    constructor(private http: HttpClient) {
    }

    getOfficeData(): Observable<OfficeData> {
        return this.http.get<OfficeData>('/api/office/');
    }

    saveOfficeData(data: OfficeData): Observable<OfficeData> {
        return this.http.post<OfficeData>('/api/office/', data);
    }

    getDoctorData(): Observable<DoctorData> {
        return this.http.get<DoctorData>('/api/doctors/');
    }

    saveDoctorData(data: DoctorData): Observable<DoctorData> {
        return this.http.post<DoctorData>('/api/doctors/', data);
    }
}