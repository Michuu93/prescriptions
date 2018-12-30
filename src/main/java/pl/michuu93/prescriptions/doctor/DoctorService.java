package pl.michuu93.prescriptions.doctor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DoctorService {
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    Doctor getDoctor() {
        List<Doctor> officeData = doctorRepository.findAll();
        return officeData.isEmpty() ? null : officeData.get(0);
    }

    Doctor upsert(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}