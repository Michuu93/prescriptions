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

        var patient = Patient.builder()
                .idType(IdentType.PERSONAL_ID)
                .id("90090515836")
                .address("Słoneczna 1")
                .birthdate(LocalDate.of(1990, 9, 5))
                .nfz("15")
                .firstName("Jan")
                .lastName("Nowak")
                .build();
        assertThat(prescription.getPatient()).isEqualTo(patient);

        var drug = Drug.builder()
                .bl7("3220322")
                .ean("5909991292935")
                .psychotrope(false)
                .senior(false)
                .vaccine(false)
                .price(0F)
                .name("Abacavir+Lamivudine Mylan")
                .internationalName("Abacavirum, Lamivudinum")
                .form("tabl.powl.")
                .dose("0,6g+0,3g")
                .packageSize("60 tabl.")
                .refunds(Lists.emptyList())
                .active(true)
                .build();

        assertThat(prescription.getDrugs()).hasSize(1);
        assertThat(prescription.getDrugs()).containsExactly(drug);
    }
}