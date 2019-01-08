package pl.michuu93.prescriptions.office;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OfficeService {
    private OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public OfficeData getOfficeData() {
        List<OfficeData> officeData = officeRepository.findAll();
        return officeData.isEmpty() ? null : officeData.get(0);
    }

    OfficeData upsertOfficeData(OfficeData officeData) {
        return officeRepository.save(officeData);
    }
}