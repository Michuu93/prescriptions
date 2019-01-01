package pl.michuu93.prescriptions.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michuu93.prescriptions.patient.model.Patient;

@Repository
interface PatientRepository extends JpaRepository<Patient, String> {
    Page<Patient> findByIdContainingIgnoreCaseOrLastNameContainingIgnoreCase(Pageable pageable, String id, String lastName);
}