import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, Subject} from "rxjs";
import {PatientsPage} from "./model/patients-page.model";
import {Patient} from "./model/patient.model";

@Injectable({providedIn: 'root'})
export class PatientsService {
    private patientEdited = new Subject<Patient>();
    patientEdited$ = this.patientEdited.asObservable();
    private editMode = new Subject<boolean>();
    editMode$ = this.editMode.asObservable();
    private patientsChange = new Subject();
    patientsChange$ = this.patientsChange.asObservable();

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

    setEditedPatient(patient: Patient) {
        this.patientEdited.next(patient);
    }

    setEditMode(isEnable: boolean) {
        this.editMode.next(isEnable);
    }

    refreshPatients() {
        this.patientsChange.next();
    }
}