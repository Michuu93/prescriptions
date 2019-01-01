package pl.michuu93.prescriptions.doctor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<Doctor> getDoctor() {
        Doctor doctor = doctorService.getDoctor();
        return Objects.nonNull(doctor) ? ResponseEntity.ok(doctor) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Doctor> upsertDoctorData(@RequestBody Doctor doctor) {
        Doctor savedData = doctorService.upsert(doctor);
        return ResponseEntity.ok(savedData);
    }
}