package pl.michuu93.prescriptions.drug.model;

import lombok.Data;
import pl.michuu93.prescriptions.drug.FloatAdapter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@Data
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Drug {
    @Id
    @XmlAttribute(name = "BL7")
    private String id;

    @XmlAttribute(name = "EAN")
    private String ean;

    @XmlAttribute(name = "psychotrop")
    private boolean psychotrope;

    @XmlAttribute(name = "senior")
    private boolean senior;

    @XmlAttribute(name = "szczepionka")
    private boolean vaccine;

    @XmlJavaTypeAdapter(FloatAdapter.class)
    @XmlAttribute(name = "cena")
    private Float price;

    @XmlElement(name = "Nazwa", required = true)
    private String name;

    @XmlElement(name = "NazwaInt", required = true)
    private String internationalName;

    @XmlElement(name = "Postać", required = true)
    private String form;

    @XmlElement(name = "Dawka", required = true)
    private String dose;

    @XmlElement(name = "Opakowanie", required = true)
    private String packageSize;

    @OneToMany
    @XmlElementWrapper(name = "Refundacja")
    @XmlElement(name = "Poziom", required = true)
    private List<Refund> refunds;
}