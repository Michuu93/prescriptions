package pl.michuu93.prescriptions.prescription.model;

import lombok.Data;
import pl.michuu93.prescriptions.office.OfficeData;
import pl.michuu93.prescriptions.patient.model.Patient;

import javax.persistence.*;

@Data
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne(targetEntity = Patient.class)
    private Patient patient;
    private OfficeData officeData;
    private PrescriptionNumber prescriptionNumber;
    @Enumerated(EnumType.STRING)
    private PrescriptionPermissions additionalPermissions = PrescriptionPermissions.X;
}