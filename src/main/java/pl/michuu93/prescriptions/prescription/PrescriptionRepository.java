package pl.michuu93.prescriptions.prescription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michuu93.prescriptions.prescription.model.Prescription;

@Repository
interface PrescriptionRepository extends JpaRepository<Prescription, String> {
    Page<Prescription> findAllByOrderByDateDesc(Pageable pageable);
    Page<Prescription> findByPatientIdContainingOrderByDateDesc(Pageable pageable, String patientId);
}