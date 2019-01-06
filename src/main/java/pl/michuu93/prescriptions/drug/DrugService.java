package pl.michuu93.prescriptions.drug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.michuu93.prescriptions.drug.model.Drug;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DrugService {
    private DrugRepository drugRepository;

    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    Page<Drug> getDrugs(Pageable pageable) {
        return drugRepository.getAllByActiveTrue(pageable);
    }

    Page<Drug> getDrugsByName(Pageable pageable, String name) {
        return drugRepository.findByNameContainingIgnoreCaseAndActiveTrue(pageable, name);
    }

    Optional<Drug> getDrugByBl7(String bl7) {
        return drugRepository.findById(bl7);
    }

    @Transactional
    DrugsUpdateResponse updateDrugs(List<Drug> drugs) {
        int updatedCount = drugRepository.updateActivityInAll(false);
        List<Drug> updatedList = drugRepository.saveAll(drugs);
        var result = DrugsUpdateResponse.builder()
                .inactivated(updatedCount)
                .active(updatedList.size())
                .build();
        log.debug("Updated drugs list [{}]", result);
        return result;
    }
}