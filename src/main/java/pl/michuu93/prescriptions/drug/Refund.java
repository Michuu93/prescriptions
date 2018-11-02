package pl.michuu93.prescriptions.drug;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
class Refund {
    @XmlAttribute(name = "poziom")
    private String level;
    @XmlValue
    private String description;
}