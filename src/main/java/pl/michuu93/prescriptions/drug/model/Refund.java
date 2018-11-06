package pl.michuu93.prescriptions.drug.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;

@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Refund {
    @Id
    @XmlTransient
    private String id;
    @XmlAttribute(name = "poziom")
    private String level;
    @XmlValue
    private String description;
}