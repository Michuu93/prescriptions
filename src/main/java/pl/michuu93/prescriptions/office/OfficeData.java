package pl.michuu93.prescriptions.office;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class OfficeData {
    @Id
    private final String id = "officeData";
    private String name;
    private String address;
    private String regon;
}
