package pl.michuu93.prescriptions.drug;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.michuu93.prescriptions.drug.model.Drug;

import javax.transaction.Transactional;

@Repository
interface DrugRepository extends JpaRepository<Drug, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Drug SET active = :isActive")
    int updateActivityInAll(@Param("isActive") boolean isActive);

    Page<Drug> getAllByActiveTrue(Pageable pageable);

    Page<Drug> findByNameContainingIgnoreCaseAndActiveTrue(Pageable pageable, String name);
}