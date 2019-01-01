package pl.michuu93.prescriptions.office;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/office")
public class OfficeController {
    private OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping
    public ResponseEntity<OfficeData> getOfficeData() {
        OfficeData officeData = officeService.getOfficeData();
        return Objects.nonNull(officeData) ? ResponseEntity.ok(officeData) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<OfficeData> upsertOfficeData(@RequestBody OfficeData officeData) {
        OfficeData savedData = officeService.upsertOfficeData(officeData);
        return ResponseEntity.ok(savedData);
    }
}