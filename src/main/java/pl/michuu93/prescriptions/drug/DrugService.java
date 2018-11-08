package pl.michuu93.prescriptions.drug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;

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

    void updateDrugs(DrugsList drugs) {
        int updatedCount = drugRepository.updateActivityInAll(false);
        List<Drug> updatedList = drugRepository.saveAll(drugs.getDrugs());
        log.debug("Update drugs list [inactivated = {}, added active = {}]", updatedCount, updatedList.size());
    }
}