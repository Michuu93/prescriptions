package pl.michuu93.prescriptions.prescription;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.michuu93.prescriptions.AbstractTest;
import pl.michuu93.prescriptions.drug.DrugsUpdateResponse;
import pl.michuu93.prescriptions.drug.model.DrugsList;
import pl.michuu93.prescriptions.prescription.model.Prescription;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PrescriptionServiceTest extends AbstractTest {
    @Test
    @Transactional
    public void shouldSaveAndFind() throws IOException, URISyntaxException, JAXBException {
        saveExampleDrug();
        saveExamplePatient();

        Prescription prescription = upsertPrescription(getExamplePrescription("example_prescription.json"));
        assertThat(prescription).isNotNull();
        String prescriptionId = prescription.getId();
        assertThat(prescriptionId).isNotNull();

        Prescription upsertedPrescriptionFromDb = upsertPrescription(prescription);
        assertThat(upsertedPrescriptionFromDb).isNotNull();
        assertThat(upsertedPrescriptionFromDb.getId()).isNotNull();

        Optional<Prescription> foundPrescriptionFromDb = prescriptionService.getPrescriptionById(prescriptionId);
        assertThat(foundPrescriptionFromDb).isPresent();
        assertThat(foundPrescriptionFromDb.get()).isEqualTo(prescription);
    }

    private void saveExamplePatient() throws IOException, URISyntaxException {
        upsertExamplePatient(getExamplePatient("valid_personal_id_birthdate.json"));
    }

    private void saveExampleDrug() throws IOException, URISyntaxException, JAXBException {
        DrugsList drugs = getExampleDrugs("example_drug.xml");
        assertThat(drugs).isNotNull();
        DrugsUpdateResponse drugsUpdateResponse = updateDrugs(drugs.getDrugs());
        assertThat(drugsUpdateResponse).isNotNull();
    }
}