package pl.michuu93.prescriptions.drug;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "Leki")
@XmlAccessorType(XmlAccessType.FIELD)
class DrugsList {
    @XmlElement(name = "Lek")
    private List<Drug> drugs;
}