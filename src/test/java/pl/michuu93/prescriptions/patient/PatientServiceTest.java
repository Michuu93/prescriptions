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
import pl.michuu93.prescriptions.exception.BirthdateException;
import pl.michuu93.prescriptions.exception.PersonalIdException;
import pl.michuu93.prescriptions.patient.model.Patient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PatientServiceTest {
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
        var patient = upsertExamplePatient("valid_personal_id_birthdate.json");
        assertThat(patient).isNotNull();
        var patientId = upsertExamplePatient("valid_personal_id_birthdate.json").getId();
        assertThat(patientId).isNotNull();

        var patientFromDb = patientService.findById(patientId);
        assertThat(patientFromDb).isPresent();
        assertThat(patientFromDb.get()).isEqualTo(patient);
    }

    @Test
    public void shouldSaveWithBirthdate() throws IOException, URISyntaxException {
        var patientFromDb = upsertExamplePatient("valid_personal_id_birthdate.json");
        assertThat(patientFromDb.getBirthdate()).isEqualTo(LocalDate.of(1990, 9, 5));
    }

    @Test
    public void shouldCalculateBirthdate() throws IOException, URISyntaxException {
        var patientFromDb = upsertExamplePatient("valid_personal_id_no_birthdate.json");
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

    @Test
    public void shouldThrowInvalidBirthdate() throws IOException, URISyntaxException {
        exceptionRule.expect(BirthdateException.class);
        exceptionRule.expectMessage("Invalid birthdate");
        upsertExamplePatient("valid_personal_id_invalid_birthdate.json");
    }

    private Patient upsertExamplePatient(String exampleFileName) throws IOException, URISyntaxException {
        var patientString = loadExampleJson(exampleFileName);
        var patient = objectMapper.readValue(patientString, Patient.class);
        return patientService.upsert(patient);
    }

    private String loadExampleJson(String fileName) throws URISyntaxException, IOException {
        var path = Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("patient/" + fileName)).toURI());
        return Files.readString(path);
    }
}
