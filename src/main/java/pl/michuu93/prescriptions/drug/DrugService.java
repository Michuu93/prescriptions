package pl.michuu93.prescriptions.drug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;

import java.util.List;

@Slf4j
@Service
public class DrugService {
    private DrugRepository drugRepository;

    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    void updateDrugs(DrugsList drugs) {
        int updatedCount = drugRepository.updateActivityInAll(false);
        List<Drug> updatedList = drugRepository.saveAll(drugs.getDrugs());
        log.debug("Update drugs list [inactivated = {}, added active = {}]", updatedCount, updatedList.size());
    }
}
