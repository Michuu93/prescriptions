package pl.michuu93.prescriptions.drug.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Refund {
    @Id
    @JsonIgnore
    @XmlTransient
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @XmlAttribute(name = "poziom")
    private String level;
    @XmlValue
    @Column(columnDefinition = "text")
    private String description;
}