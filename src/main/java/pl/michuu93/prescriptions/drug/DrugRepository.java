package pl.michuu93.prescriptions.drug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michuu93.prescriptions.drug.model.Drug;

@Repository
interface DrugRepository extends JpaRepository<Drug, String> {
}