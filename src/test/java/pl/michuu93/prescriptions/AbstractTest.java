package pl.michuu93.prescriptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.michuu93.prescriptions.drug.DrugService;
import pl.michuu93.prescriptions.drug.DrugsUpdateResponse;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;
import pl.michuu93.prescriptions.patient.PatientService;
import pl.michuu93.prescriptions.patient.model.Patient;
import pl.michuu93.prescriptions.prescription.PrescriptionService;
import pl.michuu93.prescriptions.prescription.model.Prescription;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public abstract class AbstractTest {
    @Autowired
    protected PatientService patientService;

    @Autowired
    protected PrescriptionService prescriptionService;

    @Autowired
    protected DrugService drugService;

    protected String loadExample(String filename) throws IOException, URISyntaxException {
        var path = Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(filename)).toURI());
        return Files.readString(path);
    }

    protected Patient getExamplePatient(String exampleFileName) throws IOException, URISyntaxException {
        String patientString = loadExample("patient/" + exampleFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(patientString, Patient.class);
    }

    protected Prescription getExamplePrescription(String exampleFileName) throws IOException, URISyntaxException {
        String prescriptionString = loadExample("prescription/" + exampleFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(prescriptionString, Prescription.class);
    }

    protected DrugsList getExampleDrugs(String exampleFileName) throws IOException, URISyntaxException, JAXBException {
        String drugString = loadExample("drug/" + exampleFileName);
        var context = JAXBContext.newInstance(DrugsList.class);
        var marshaller = context.createUnmarshaller();
        return (DrugsList) marshaller.unmarshal(new StringReader(drugString));
    }

    protected Patient upsertExamplePatient(Patient patient) {
        return patientService.upsertPatient(patient);
    }

    protected Prescription upsertPrescription(Prescription prescription) {
        return prescriptionService.upsertPrescription(prescription);
    }

    protected DrugsUpdateResponse updateDrugs(List<Drug> drugs) {
        return drugService.updateDrugs(drugs);
    }
}