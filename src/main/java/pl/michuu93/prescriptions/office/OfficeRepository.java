package pl.michuu93.prescriptions.office;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<OfficeData, String> {
}
