package pl.michuu93.prescriptions.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.michuu93.prescriptions.exception.PeselException;
import pl.michuu93.prescriptions.exception.BirthdateException;
import pl.michuu93.prescriptions.patient.model.IdentType;
import pl.michuu93.prescriptions.patient.model.Patient;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient upsertPatient(Patient patient) {
        if (IdentType.PERSONAL_ID.equals(patient.getIdType())) {
            validatePesel(patient);
            setBirthdate(patient);
        }

        if (isNull(patient.getBirthdate())) {
            throw new BirthdateException("No birth date");
        }
        return patientRepository.saveAndFlush(patient);
    }

    private void validatePesel(Patient patient) {
        if (!PeselUtils.isValidPesel(patient.getId())) {
            throw new PeselException("Invalid pesel");
        }
    }

    private void setBirthdate(Patient patient) {
        if (isNull(patient.getBirthdate())) {
            LocalDate birthdate = PeselUtils.calculateBirthdate(patient.getId());
            patient.setBirthdate(birthdate);
        } else {
            LocalDate birthdate = patient.getBirthdate();
            LocalDate birthdateFromPesel = PeselUtils.calculateBirthdate(patient.getId());
            if (!birthdateFromPesel.equals(birthdate)) {
                throw new BirthdateException("Invalid birth date");
            }
        }
    }
}