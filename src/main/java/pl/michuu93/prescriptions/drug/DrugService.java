package pl.michuu93.prescriptions.drug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
        return drugRepository.findAll(pageable);
    }

    Optional<Drug> getDrugByBl7(String bl7) {
        return drugRepository.findById(bl7);
    }

    DrugsUpdateResponse updateDrugs(List<Drug> drugs) {
        var updatedCount = drugRepository.updateActivityInAll(false);
        var updatedList = drugRepository.saveAll(drugs);
        var result = DrugsUpdateResponse.builder()
                .inactivated(updatedCount)
                .active(updatedList.size())
                .build();
        log.debug("Update drugs list [{}]", result);
        return result;
    }
}