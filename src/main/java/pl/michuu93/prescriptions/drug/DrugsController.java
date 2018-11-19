package pl.michuu93.prescriptions.drug;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;

@RestController
@RequestMapping("/drugs")
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
        var optionalMovie = drugService.getDrugByBl7(bl7);
        return optionalMovie.isPresent() ? ResponseEntity.ok(optionalMovie.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DrugsUpdateResponse> updateDrugs(@RequestBody DrugsList drugs) {
        var response = drugService.updateDrugs(drugs.getDrugs());
        return ResponseEntity.ok(response);
    }
}