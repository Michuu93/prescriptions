package pl.michuu93.prescriptions.prescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumberList;

@Slf4j
@RestController
@RequestMapping("/api//prescriptions")
public class PrescriptionController {
    private PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/numbers")
    public ResponseEntity<Integer> savePrescriptionNumbers(@RequestBody PrescriptionNumberList prescriptionNumbers) {
        int savedNumber = prescriptionService.saveNumbers(prescriptionNumbers);
        return ResponseEntity.ok(savedNumber);
    }
}
