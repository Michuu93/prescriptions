package pl.michuu93.prescriptions.prescription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumber;

@Repository
interface PrescriptionNumberRepository extends JpaRepository<PrescriptionNumber, String> {
}