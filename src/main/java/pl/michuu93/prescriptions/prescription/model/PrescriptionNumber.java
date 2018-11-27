package pl.michuu93.prescriptions.prescription.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Enumerated(EnumType.STRING)
    private PrescriptionType type;
    @Enumerated(EnumType.STRING)
    private PrescriptionCategory category;
    private String number;

    PrescriptionNumber(PrescriptionType type, PrescriptionCategory category, String number) {
        this.type = type;
        this.category = category;
        this.number = number;
    }
}