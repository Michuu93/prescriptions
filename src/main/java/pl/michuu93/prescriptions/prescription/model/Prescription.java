package pl.michuu93.prescriptions.prescription.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(indexes = {
        @Index(columnList = "patientId")
})
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String patientId;


}