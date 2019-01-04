package pl.michuu93.prescriptions.drug.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import pl.michuu93.prescriptions.drug.adapter.BooleanAdapter;
import pl.michuu93.prescriptions.drug.adapter.FloatAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@Data
@Entity
@Table(indexes = {
        @Index(columnList = "name")
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Drug {
    @Id
    @XmlAttribute(name = "BL7")
    private String bl7;

    @XmlAttribute(name = "EAN")
    private String ean;

    @XmlJavaTypeAdapter(BooleanAdapter.class)
    @XmlAttribute(name = "psychotrop")
    private Boolean psychotrope;

    @XmlJavaTypeAdapter(BooleanAdapter.class)
    @XmlAttribute(name = "senior")
    private Boolean senior;

    @XmlJavaTypeAdapter(BooleanAdapter.class)
    @XmlAttribute(name = "szczepionka")
    private Boolean vaccine;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElementWrapper(name = "Refundacja")
    @XmlElement(name = "Poziom", required = true)
    private List<Refund> refunds;

    @XmlTransient
    @ColumnDefault("false")
    private boolean active = true;

    public boolean isPsychotrope() {
        return psychotrope;
    }

    public boolean isSenior() {
        return senior;
    }

    public boolean isVaccine() {
        return vaccine;
    }
}