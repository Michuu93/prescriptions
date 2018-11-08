package pl.michuu93.prescriptions.drug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michuu93.prescriptions.drug.model.DrugsList;

@Slf4j
@RestController
@RequestMapping("/drug")
public class DrugsController {
    private DrugService drugService;

    public DrugsController(DrugService drugService) {
        this.drugService = drugService;
    }

    @PostMapping
    public ResponseEntity updateDrugs(@RequestBody DrugsList drugs) {
        log.debug("Received drugs list [size = {}]", drugs.getDrugs().size());
        drugService.updateDrugs(drugs);
        return ResponseEntity.ok().build();
    }
}