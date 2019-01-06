package pl.michuu93.prescriptions.prescription;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.michuu93.prescriptions.doctor.DoctorService;
import pl.michuu93.prescriptions.office.OfficeService;
import pl.michuu93.prescriptions.prescription.model.Prescription;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumber;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumberList;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class PrescriptionService {
    private PrescriptionNumberRepository prescriptionNumberRepository;
    private PrescriptionRepository prescriptionRepository;
    private OfficeService officeService;
    private DoctorService doctorService;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionNumberRepository prescriptionNumberRepository,
                               OfficeService officeService, DoctorService doctorService) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionNumberRepository = prescriptionNumberRepository;
        this.officeService = officeService;
        this.doctorService = doctorService;
    }

    int saveNumbers(PrescriptionNumberList prescriptionNumbers) {
        List<PrescriptionNumber> numbers = prescriptionNumbers.getPrescriptionNumbers();
        List<PrescriptionNumber> savedNumbers = prescriptionNumberRepository.saveAll(numbers);
        int savedNumber = savedNumbers.size();
        log.debug("Added prescription numbers [size={}]", savedNumber);
        return savedNumber;
    }

    Page<Prescription> getPrescriptions(Pageable pageable) {
        return prescriptionRepository.findAllByOrderByDateDesc(pageable);
    }

    Page<Prescription> getPrescriptionsByPatientId(Pageable pageable, String patientId) {
        return prescriptionRepository.findByPatientIdContainingOrderByDateDesc(pageable, patientId);
    }

    @Transactional
    Prescription upsertPrescription(Prescription prescription) {
        if (isNull(prescription.getDate())) {
            prescription.setDate(LocalDate.now());
        }
        if (isNull(prescription.getId())) {
            prescription.setId(UUID.randomUUID().toString());
            log.info("Saving prescription {}", prescription);
        } else {
            log.info("Upserting prescription {}", prescription);
        }
        prescription.setOfficeData(officeService.getOfficeData());
        prescription.setDoctor(doctorService.getDoctor());
        return prescriptionRepository.save(prescription);
    }
}