package pl.michuu93.prescriptions.prescription;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.util.Optional;
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

    Optional<Prescription> getPrescriptionById(String id) {
        return prescriptionRepository.findById(id);
    }

    Page<Prescription> getPrescriptions(Pageable pageable) {
        return prescriptionRepository.findAllByOrderByDateDesc(pageable);
    }

    Page<Prescription> getPrescriptionsByPatientId(Pageable pageable, String patientId) {
        return prescriptionRepository.findByPatientIdContainingOrderByDateDesc(pageable, patientId);
    }

    @Transactional
    public Prescription upsertPrescription(Prescription prescription) {
        fillPrescription(prescription);
        if (isNull(prescription.getId())) {
            prescription.setId(UUID.randomUUID().toString());
            log.info("Saving prescription {}", prescription);
        } else {
            log.info("Upserting prescription {}", prescription);
        }
        return prescriptionRepository.save(prescription);
    }

    private void fillPrescription(Prescription prescription) {
        setPrescriptionNumber(prescription);
        setPrescriptionDate(prescription);
        setOfficeData(prescription);
        setDoctor(prescription);
    }

    private void setDoctor(Prescription prescription) {
        prescription.setDoctor(doctorService.getDoctor());
    }

    private void setOfficeData(Prescription prescription) {
        prescription.setOfficeData(officeService.getOfficeData());
    }

    private void setPrescriptionDate(Prescription prescription) {
        if (isNull(prescription.getDate())) {
            prescription.setDate(LocalDate.now());
        }
    }

    private void setPrescriptionNumber(Prescription prescription) {
        if (isNull(prescription.getPrescriptionNumber())) {
            prescription.setPrescriptionNumber(RandomStringUtils.randomNumeric(20));
        }
    }
}