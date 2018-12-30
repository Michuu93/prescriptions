package pl.michuu93.prescriptions.doctor;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Doctor {
    @Id
    private final String id = "doctorData";
    private String license;
    private String firstName;
    private String lastName;
}