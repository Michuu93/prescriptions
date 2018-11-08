package pl.michuu93.prescriptions.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PeselUtilsTest {
    private static final String VALID_PESEL = "90090515836";
    private static final String INVALID_PESEL = "91090515836";

    @Test
    public void shouldCalculateBirthdate() {
        LocalDate birthdate = LocalDate.of(1990, 9, 5);
        LocalDate calculatedBirthdate = PeselUtils.calculateBirthdate(VALID_PESEL);
        assertThat(calculatedBirthdate).isEqualTo(birthdate);
    }

    @Test
    public void shouldValidateEmail() {
        boolean isValidPesel = PeselUtils.isValidPesel(VALID_PESEL);
        assertThat(isValidPesel).isTrue();

        isValidPesel = PeselUtils.isValidPesel(INVALID_PESEL);
        assertThat(isValidPesel).isFalse();
    }
}
