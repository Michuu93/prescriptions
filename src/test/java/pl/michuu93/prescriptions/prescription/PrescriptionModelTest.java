package pl.michuu93.prescriptions.prescription;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Test;
import pl.michuu93.prescriptions.AbstractTest;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.patient.model.IdentType;
import pl.michuu93.prescriptions.patient.model.Patient;
import pl.michuu93.prescriptions.prescription.model.Prescription;
import pl.michuu93.prescriptions.prescription.model.PrescriptionPermissions;
import pl.michuu93.prescriptions.prescription.model.PrescriptionType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PrescriptionModelTest extends AbstractTest {
    @Test
    public void shouldDeserializeJsonToObject() throws IOException, URISyntaxException {
        String exampleJson = loadExample("prescription/example_prescription.json");
        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        Prescription prescription = objectMapper.readValue(exampleJson, Prescription.class);
        assertThat(prescription).isNotNull();
        assertThat(prescription.getAdditionalPermissions()).isEqualTo(PrescriptionPermissions.X);
        assertThat(prescription.getPrescriptionType()).isEqualTo(PrescriptionType.L);

        var patient = new Patient();
        patient.setIdType(IdentType.PERSONAL_ID);
        patient.setId("90090515836");
        patient.setAddress("Słoneczna 1");
        patient.setBirthdate(LocalDate.of(1990, 9, 5));
        patient.setNfz("15");
        patient.setFirstName("Jan");
        patient.setLastName("Nowak");
        assertThat(prescription.getPatient()).isEqualTo(patient);

        var drug = new Drug();
        drug.setBl7("3220322");
        drug.setEan("5909991292935");
        drug.setPsychotrope(false);
        drug.setSenior(false);
        drug.setVaccine(false);
        drug.setPrice(0F);
        drug.setName("Abacavir+Lamivudine Mylan");
        drug.setInternationalName("Abacavirum, Lamivudinum");
        drug.setForm("tabl.powl.");
        drug.setDose("0,6g+0,3g");
        drug.setPackageSize("60 tabl.");
        drug.setActive(true);
        drug.setRefunds(Lists.emptyList());

        assertThat(prescription.getDrugs()).hasSize(1);
        assertThat(prescription.getDrugs()).containsExactly(drug);
    }
}