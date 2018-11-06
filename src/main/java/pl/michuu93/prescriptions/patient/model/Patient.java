package pl.michuu93.prescriptions.patient.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity
public class Patient {
    @Id
    @Builder.Default()
    private String id = UUID.randomUUID().toString();
    @Builder.Default
    private IdentType identType = IdentType.PERSONAL_ID;
    private String personalId;
    private String parentId;
    private String passportNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String address;
}