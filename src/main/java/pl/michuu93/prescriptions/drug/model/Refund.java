package pl.michuu93.prescriptions.drug.model;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Refund {
    @Id
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @XmlAttribute(name = "poziom")
    private String level;
    @XmlValue
    @Column(columnDefinition = "text")
    private String description;
}