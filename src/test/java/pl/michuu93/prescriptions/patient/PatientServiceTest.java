package pl.michuu93.prescriptions.patient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.michuu93.prescriptions.AbstractTest;
import pl.michuu93.prescriptions.exception.BirthdateException;
import pl.michuu93.prescriptions.exception.PersonalIdException;
import pl.michuu93.prescriptions.patient.model.Patient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientServiceTest extends AbstractTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    private PatientService patientService;

    private ObjectMapper objectMapper;

    @Before
    public void before() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldSaveAndFind() throws IOException, URISyntaxException {
        Patient patient = upsertExamplePatient("valid_personal_id_birthdate.json");
        assertThat(patient).isNotNull();
        String patientId = upsertExamplePatient("valid_personal_id_birthdate.json").getId();
        assertThat(patientId).isNotNull();

        Optional<Patient> patientFromDb = patientService.findById(patientId);
        assertThat(patientFromDb).isPresent();
        assertThat(patientFromDb.get()).isEqualTo(patient);
    }

    @Test
    public void shouldSaveWithBirthdate() throws IOException, URISyntaxException {
        Patient patientFromDb = upsertExamplePatient("valid_personal_id_birthdate.json");
        assertThat(patientFromDb.getBirthdate()).isEqualTo(LocalDate.of(1990, 9, 5));
    }

    @Test
    public void shouldCalculateBirthdate() throws IOException, URISyntaxException {
        Patient patientFromDb = upsertExamplePatient("valid_personal_id_no_birthdate.json");
        assertThat(patientFromDb.getBirthdate()).isEqualTo(LocalDate.of(1990, 9, 5));
    }

    @Test
    public void shouldThrowInvalidPersonalId() throws IOException, URISyntaxException {
        exceptionRule.expect(PersonalIdException.class);
        exceptionRule.expectMessage("Invalid personal id");
        upsertExamplePatient("invalid_personal_id.json");
    }

    @Test
    public void shouldThrowNoBirthdate() throws IOException, URISyntaxException {
        exceptionRule.expect(BirthdateException.class);
        exceptionRule.expectMessage("No birthdate");
        upsertExamplePatient("valid_passport_no_birthdate.json");
    }

    private Patient upsertExamplePatient(String exampleFileName) throws IOException, URISyntaxException {
        String patientString = loadExample("patient/" + exampleFileName);
        Patient patient = objectMapper.readValue(patientString, Patient.class);
        return patientService.upsertPatient(patient);
    }
}