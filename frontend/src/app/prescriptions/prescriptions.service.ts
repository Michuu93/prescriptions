import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable, Subject} from "rxjs";
import {Prescription} from "./model/prescription-model";
import {PrescriptionsPage} from "./model/prescriptions-page.model";

@Injectable({providedIn: 'root'})
export class PrescriptionsService {
    private prescriptionsEdited = new Subject<Prescription>();
    prescriptionsEdited$ = this.prescriptionsEdited.asObservable();
    private editMode = new Subject<boolean>();
    editMode$ = this.editMode.asObservable();
    private prescriptionsChange = new Subject();
    prescriptionsChange$ = this.prescriptionsChange.asObservable();

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

    refreshPrescriptions() {
        this.prescriptionsChange.next();
    }

    setEditMode(isEnable: boolean) {
        this.editMode.next(isEnable);
    }

    setEditedPrescription(prescription: Prescription) {
        this.prescriptionsEdited.next(prescription);
    }
}