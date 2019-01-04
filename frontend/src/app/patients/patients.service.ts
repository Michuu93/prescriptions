import {HttpClient} from "@angular/common/http";
import {EventEmitter, Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {PatientsPage} from "./model/patients-page.model";
import {Patient} from "./model/patient.model";

@Injectable({providedIn: 'root'})
export class PatientsService {
    patientEdited = new EventEmitter<Patient>();
    editMode = new EventEmitter<boolean>();
    patientsChange = new EventEmitter();

    constructor(private http: HttpClient) {
    }

    getPatientsPage(page: number, size: number, name: string): Observable<PatientsPage> {
        if (name) {
            return this.http.get<PatientsPage>(`/api/patients/search/${name}/?page=${page}&size=${size}`);
        } else {
            return this.http.get<PatientsPage>(`/api/patients/?page=${page}&size=${size}`);
        }
    }

    savePatient(patient: Patient): Observable<Patient> {
        return this.http.post<Patient>(`/api/patients/`, patient);
    }

    deletePatient(patient: Patient): Observable<any> {
        return this.http.delete(`/api/patients/${patient.id}`);
    }
}