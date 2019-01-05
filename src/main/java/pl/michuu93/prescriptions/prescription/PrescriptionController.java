package pl.michuu93.prescriptions.prescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michuu93.prescriptions.prescription.model.Prescription;
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

    @GetMapping
    public Page<Prescription> getPrescriptions(Pageable pageable) {
        return prescriptionService.getPrescriptions(pageable);
    }

    @GetMapping("/search/{patientId}")
    public Page<Prescription> getPrescriptions(Pageable pageable, @PathVariable String patientId) {
        return prescriptionService.getPrescriptionsByPatientId(pageable, patientId);
    }

    @PostMapping
    public ResponseEntity<Prescription> savePrescription(@RequestBody Prescription prescription) {
        Prescription result = prescriptionService.savePrescription(prescription);
        return ResponseEntity.ok(result);
    }
}