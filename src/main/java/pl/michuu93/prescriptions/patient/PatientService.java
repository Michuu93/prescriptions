package pl.michuu93.prescriptions.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.michuu93.prescriptions.exception.BirthdateException;
import pl.michuu93.prescriptions.exception.PersonalIdException;
import pl.michuu93.prescriptions.patient.model.IdentType;
import pl.michuu93.prescriptions.patient.model.Patient;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    Page<Patient> getPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    Optional<Patient> findById(String id) {
        return patientRepository.findById(id);
    }

    Patient upsert(Patient patient) {
        if (IdentType.PERSONAL_ID.equals(patient.getIdType())) {
            validatePesel(patient);
            setBirthdate(patient);
        }

        if (isNull(patient.getBirthdate())) {
            throw new BirthdateException("No birthdate");
        }
        return patientRepository.saveAndFlush(patient);
    }

    private void validatePesel(Patient patient) {
        if (!PeselUtils.isValidPesel(patient.getId())) {
            throw new PersonalIdException("Invalid personal id");
        }
    }

    private void setBirthdate(Patient patient) {
        LocalDate birthdate = PeselUtils.calculateBirthdate(patient.getId());
        patient.setBirthdate(birthdate);
    }
}