package pl.michuu93.prescriptions.prescription;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pl.michuu93.prescriptions.AbstractTest;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;
import pl.michuu93.prescriptions.patient.model.Patient;
import pl.michuu93.prescriptions.prescription.model.Prescription;
import pl.michuu93.prescriptions.prescription.model.PrescriptionPermissions;
import pl.michuu93.prescriptions.prescription.model.PrescriptionType;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class PrescriptionModelTest extends AbstractTest {
    @Test
    public void shouldDeserializeJsonToObject() throws IOException, URISyntaxException, JAXBException {
        String exampleJson = loadExample("prescription/example_prescription.json");
        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        Prescription prescription = objectMapper.readValue(exampleJson, Prescription.class);
        assertThat(prescription).isNotNull();
        assertThat(prescription.getAdditionalPermissions()).isEqualTo(PrescriptionPermissions.X);
        assertThat(prescription.getPrescriptionType()).isEqualTo(PrescriptionType.L);

        Patient patient = getExamplePatient("valid_personal_id_birthdate.json");
        assertThat(prescription.getPatient()).isEqualTo(patient);

        DrugsList drugs = getExampleDrugs("example_drug.xml");
        assertThat(drugs).isNotNull();
        assertThat(drugs.getDrugs()).hasSize(1);

        Drug drug = drugs.getDrugs().get(0);

        assertThat(prescription.getDrugs()).hasSize(1);
        assertThat(prescription.getDrugs()).containsExactly(drug);
    }
}