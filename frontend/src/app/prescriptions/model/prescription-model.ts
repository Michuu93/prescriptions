import {Patient} from "../../patients/model/patient.model";
import {Drug} from "../../drugs/model/drug.model";

export class Prescription {
    public id: string;
    public prescriptionNumber: string;
    public patient: Patient;
    public additionalPermissions = 'X';
    public prescriptionType = 'L';
    public drugs: Drug[];
    public date: string;
}

export enum PrescriptionPermissions {
    'X', 'S', 'IB', 'IW', 'ZK', 'AZ', 'WP', 'PO', 'CN', 'DN', 'IN'
}

export enum PrescriptionType {
    'L', 'S'
}