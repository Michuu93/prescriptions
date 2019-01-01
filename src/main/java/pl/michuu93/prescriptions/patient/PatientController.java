package pl.michuu93.prescriptions.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michuu93.prescriptions.patient.model.Patient;

@Slf4j
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public Page<Patient> getPatients(Pageable pageable) {
        return patientService.getPatients(pageable);
    }

    @GetMapping("/search/{searchValue}")
    public Page<Patient> getPatients(Pageable pageable, @PathVariable String searchValue) {
        return patientService.getPatientsByIdOrLastName(pageable, searchValue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable String id) {
        return patientService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> upsertPatient(@RequestBody Patient patient) {
        Patient result = patientService.upsertPatient(patient);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}