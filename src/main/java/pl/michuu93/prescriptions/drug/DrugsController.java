package pl.michuu93.prescriptions.drug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/drug")
public class DrugsController {
    private DrugService drugService;

    public DrugsController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public Page<Drug> getDrugs(Pageable pageable) {
        return drugService.getDrugs(pageable);
    }

    @GetMapping("/{bl7}")
    public ResponseEntity<Drug> getDrugByBl7(@PathVariable String bl7) {
        Optional<Drug> optionalMovie = drugService.getDrugByBl7(bl7);
        return optionalMovie.isPresent() ? ResponseEntity.ok(optionalMovie.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity updateDrugs(@RequestBody DrugsList drugs) {
        log.debug("Received drugs list [size = {}]", drugs.getDrugs().size());
        drugService.updateDrugs(drugs);
        return ResponseEntity.ok().build();
    }
}