package pl.michuu93.prescriptions.prescription.model;

import lombok.Data;
import pl.michuu93.prescriptions.doctor.Doctor;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.office.OfficeData;
import pl.michuu93.prescriptions.patient.model.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Prescription {
    @Id
    private String id;
    @ManyToOne(targetEntity = Patient.class)
    private Patient patient;
    @ManyToOne(targetEntity = OfficeData.class)
    private OfficeData officeData;
    @ManyToOne(targetEntity = Doctor.class)
    private Doctor doctor;
    private String prescriptionNumber;
    @Enumerated(EnumType.STRING)
    private PrescriptionPermissions additionalPermissions = PrescriptionPermissions.X;
    @Enumerated(EnumType.STRING)
    private PrescriptionCategory prescriptionCategory;
    @Enumerated(EnumType.STRING)
    private PrescriptionType prescriptionType;
    @ManyToMany
    private List<Drug> drugs;
    private LocalDate date;
    private LocalDate dateFromDay;
}