package pl.michuu93.prescriptions.prescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumber;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumberList;

import java.util.List;

@Slf4j
@Service
public class PrescriptionService {
    private PrescriptionNumberRepository prescriptionNumberRepository;

    public PrescriptionService(PrescriptionNumberRepository prescriptionNumberRepository) {
        this.prescriptionNumberRepository = prescriptionNumberRepository;
    }

    int saveNumbers(PrescriptionNumberList prescriptionNumbers) {
        List<PrescriptionNumber> numbers = prescriptionNumbers.getPrescriptionNumbers();
        List<PrescriptionNumber> savedNumbers = prescriptionNumberRepository.saveAll(numbers);
        int savedNumber = savedNumbers.size();
        log.debug("Add prescription numbers [size={}]", savedNumber);
        return savedNumber;
    }
}