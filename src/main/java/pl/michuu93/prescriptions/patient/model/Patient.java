package pl.michuu93.prescriptions.patient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private IdentType idType = IdentType.PERSONAL_ID;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String address;
    private String nfz;
}