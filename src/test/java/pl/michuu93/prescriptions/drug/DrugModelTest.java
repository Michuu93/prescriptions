package pl.michuu93.prescriptions.drug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;
import pl.michuu93.prescriptions.drug.model.Refund;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DrugModelTest {
    @Test
    public void shouldDeserializeXmlToObject() throws IOException, URISyntaxException, JAXBException {
        String exampleXml = loadTestXml();
        JAXBContext context = JAXBContext.newInstance(DrugsList.class);
        Unmarshaller marshaller = context.createUnmarshaller();

        DrugsList drugs = (DrugsList) marshaller.unmarshal(new StringReader(exampleXml));
        assertThat(drugs.getDrugs()).hasSize(51497);

        Drug drug = drugs.getDrugs().get(2838);
        assertThat(drug.getEan())
                .isEqualTo("5909991209483");
        assertThat(drug.isPsychotrope())
                .isEqualTo(false);
        assertThat(drug.isSenior())
                .isEqualTo(false);
        assertThat(drug.isVaccine())
                .isEqualTo(false);
        assertThat(drug.getPrice())
                .isEqualTo(30.43f);
        assertThat(drug.getName())
                .isEqualTo("Augmentin");
        assertThat(drug.getInternationalName())
                .isEqualTo("Amoxicillinum, Acidum clavulanicum");
        assertThat(drug.getForm())
                .isEqualTo("tabl.powl.");
        assertThat(drug.getDose())
                .isEqualTo("0,875g+0,125g");
        assertThat(drug.getPackageSize())
                .isEqualTo("14 tabl.");
        assertThat(drug.getRefunds())
                .extracting(Refund::getLevel)
                .containsOnly("50%", "50%");
    }

    private String loadTestXml() throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("drugs.xml")).toURI());
        return Files.readString(path);
    }
}