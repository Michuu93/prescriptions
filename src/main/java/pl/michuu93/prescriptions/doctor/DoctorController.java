package pl.michuu93.prescriptions.doctor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{license}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable String license) {
        Optional<Doctor> optionalDoctor = doctorService.findByLicense(license);
        return optionalDoctor.isPresent() ? ResponseEntity.ok(optionalDoctor.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Doctor> upsertDoctor(@RequestBody Doctor doctor) {
        Doctor result = doctorService.upsert(doctor);
        return ResponseEntity.ok(result);
    }
}