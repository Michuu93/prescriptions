package pl.michuu93.prescriptions.patient;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@UtilityClass
public class PeselUtils {
    boolean isValidPesel(String pesel) {
        if (isNull(pesel)) {
            return false;
        }

        int pSize = pesel.length();
        if (pSize != 11) {
            return false;
        }
        int[] weights = {1,3,7,9,1,3,7,9,1,3};
        int j, sum = 0;
        int cSum = Integer.valueOf(pesel.substring(pSize - 1));
        for (int i = 0; i < pSize - 1; i++) {
            char c = pesel.charAt(i);
            j = Integer.valueOf(String.valueOf(c));
            sum += j * weights[i];
        }
        int control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == cSum);
    }

    public LocalDate calculateBirthdate(String pesel) {
        int year = getBirthYear(pesel);
        int month = Integer.parseInt(pesel.substring(2, 4));
        int dayOfMonth = Integer.parseInt(pesel.substring(4, 6));
        return LocalDate.of(year, month, dayOfMonth);
    }

    private int getBirthYear(String pesel) {
        int year = 10 * Integer.parseInt(pesel.substring(0, 1));
        year += Integer.parseInt(pesel.substring(1, 2));
        int month = 10 * Integer.parseInt(pesel.substring(2, 3));
        month += Integer.parseInt(pesel.substring(3, 4));

        if (month > 80 && month < 93) {
            year += 1800;
        } else if (month > 0 && month < 13) {
            year += 1900;
        } else if (month > 20 && month < 33) {
            year += 2000;
        } else if (month > 40 && month < 53) {
            year += 2100;
        } else if (month > 60 && month < 73) {
            year += 2200;
        }
        return year;
    }
}
