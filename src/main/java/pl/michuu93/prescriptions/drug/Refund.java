package pl.michuu93.prescriptions.drug;

import lombok.ToString;

import javax.xml.bind.annotation.*;

@ToString
class Refund {
    @XmlAttribute(name = "poziom")
    private String level;
    @XmlValue
    private String description;
}