package pl.michuu93.prescriptions.prescription.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@XmlRootElement(name = "recepty")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrescriptionNumberList {
    @XmlAttribute(name = "typ", required = true)
    private PrescriptionType type;
    @XmlAttribute(name = "kat", required = true)
    private PrescriptionCategory category;
    @XmlElementWrapper(name = "lekarz")
    @XmlElement(name = "n", required = true)
    private List<String> numbers;

    public List<PrescriptionNumber> getPrescriptionNumbers() {
        return numbers.stream()
                .map(n -> new PrescriptionNumber(this.type, this.category, n))
                .collect(Collectors.toList());
    }
}