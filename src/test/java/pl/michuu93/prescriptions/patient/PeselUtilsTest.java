package pl.michuu93.prescriptions.patient;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PeselUtilsTest {
    private static final String VALID_PESEL = "90090515836";
    private static final String INVALID_PESEL = "91090515836";

    @Test
    public void shouldCalculateBirthdate() {
        var birthdate = LocalDate.of(1990, 9, 5);
        var calculatedBirthdate = PeselUtils.calculateBirthdate(VALID_PESEL);
        assertThat(calculatedBirthdate).isEqualTo(birthdate);
    }

    @Test
    public void shouldValidateEmail() {
        var isValidPesel = PeselUtils.isValidPesel(VALID_PESEL);
        assertThat(isValidPesel).isTrue();

        isValidPesel = PeselUtils.isValidPesel(INVALID_PESEL);
        assertThat(isValidPesel).isFalse();
    }
}