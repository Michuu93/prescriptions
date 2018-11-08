package pl.michuu93.prescriptions.patient.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
@Entity
public class Patient {
    @Id
    private String id;
    @Builder.Default
    private IdentType idType = IdentType.PERSONAL_ID;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String address;
}