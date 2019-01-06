import {HttpClient} from "@angular/common/http";
import {EventEmitter, Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Prescription} from "./model/prescription-model";
import {PrescriptionsPage} from "./model/prescriptions-page.model";

@Injectable({providedIn: 'root'})
export class PrescriptionsService {
    prescriptionsEdited = new EventEmitter<Prescription>();
    editMode = new EventEmitter<boolean>();
    prescriptionsChange = new EventEmitter();

    constructor(private http: HttpClient) {
    }

    getPrescriptionsPage(page: number, size: number, patientId: string): Observable<PrescriptionsPage> {
        if (patientId) {
            return this.http.get<PrescriptionsPage>(`/api/prescriptions/search/${patientId}/?page=${page}&size=${size}`);
        } else {
            return this.http.get<PrescriptionsPage>(`/api/prescriptions/?page=${page}&size=${size}`);
        }
    }

    savePrescription(prescription: Prescription): Observable<Prescription> {
        return this.http.post<Prescription>(`/api/prescriptions/`, prescription);
    }

    printPrescription(prescription: Prescription) {

    }
}