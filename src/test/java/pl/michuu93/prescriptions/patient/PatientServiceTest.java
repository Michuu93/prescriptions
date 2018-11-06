package pl.michuu93.prescriptions.patient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class PatientServiceTest {
    private static final String VALID_PESEL = "90090515836";
    private static final String INVALID_PESEL = "91090515836";

    @Autowired
    private PatientService patientService;

    @Test
    public void shouldValidateEmail() {
        boolean isValidPesel = patientService.isValidPesel(VALID_PESEL);
        assertThat(isValidPesel).isTrue();

        isValidPesel = patientService.isValidPesel(INVALID_PESEL);
        assertThat(isValidPesel).isFalse();
    }
}
