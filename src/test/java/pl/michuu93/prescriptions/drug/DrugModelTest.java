package pl.michuu93.prescriptions.drug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootTest
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
//        assertThat(drug.getRefunds())
//                .extracting(Drug.Refunds::getContent)
//                .isEqualTo("14 tabl.");

        System.out.println(drug);
    }

    private String loadTestXml() throws IOException, URISyntaxException {
        URL resource = ClassLoader.getSystemClassLoader().getResource("drugs.xml");
        return Files.readString(Paths.get(Objects.requireNonNull(resource).toURI()), StandardCharsets.UTF_8);
    }
}