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
@RequestMapping("/drugs")
public class DrugsController {

    @PostMapping("/update")
    public ResponseEntity updateDrugs(@RequestBody DrugsList drugs) {
        log.debug("Received drugs list [size = " + drugs.getDrugs().size() + ']');
        return ResponseEntity.accepted().build();
    }
}