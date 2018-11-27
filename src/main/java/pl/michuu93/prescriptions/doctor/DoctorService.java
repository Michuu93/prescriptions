package pl.michuu93.prescriptions.doctor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.michuu93.prescriptions.exception.DoctorException;

import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    Optional<Doctor> findByLicense(String license) {
        return doctorRepository.findById(license);
    }

    Doctor upsert(Doctor doctor) {
        if (isNull(doctor.getLicense())) {
            throw new DoctorException("No license");
        }
        return doctorRepository.saveAndFlush(doctor);
    }
}