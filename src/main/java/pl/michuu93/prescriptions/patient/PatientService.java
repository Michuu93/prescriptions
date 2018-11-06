package pl.michuu93.prescriptions.patient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatientService {

    boolean isValidPesel(String pesel) {
        int pSize = pesel.length();
        if (pSize != 11) {
            return false;
        }
        int[] weights = {1,3,7,9,1,3,7,9,1,3};
        int j, control, sum = 0;
        int cSum = Integer.valueOf(pesel.substring(pSize - 1));
        for (int i = 0; i < pSize - 1; i++) {
            char c = pesel.charAt(i);
            j = Integer.valueOf(String.valueOf(c));
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == cSum);
    }
}
