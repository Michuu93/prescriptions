package pl.michuu93.prescriptions.prescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.michuu93.prescriptions.prescription.model.Prescription;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumber;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumberList;

import java.util.List;

@Slf4j
@Service
public class PrescriptionService {
    private PrescriptionNumberRepository prescriptionNumberRepository;
    private PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionNumberRepository prescriptionNumberRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionNumberRepository = prescriptionNumberRepository;
    }

    int saveNumbers(PrescriptionNumberList prescriptionNumbers) {
        List<PrescriptionNumber> numbers = prescriptionNumbers.getPrescriptionNumbers();
        List<PrescriptionNumber> savedNumbers = prescriptionNumberRepository.saveAll(numbers);
        int savedNumber = savedNumbers.size();
        log.debug("Add prescription numbers [size={}]", savedNumber);
        return savedNumber;
    }

    Page<Prescription> getPrescriptions(Pageable pageable) {
        return prescriptionRepository.findAll(pageable);
    }

    Page<Prescription> getPrescriptionsByPatientId(Pageable pageable, String patientId) {
        return prescriptionRepository.findByPatient_Id(pageable, patientId);
    }

    Prescription savePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }
}