package pl.michuu93.prescriptions.drug;

import org.junit.Test;
import pl.michuu93.prescriptions.AbstractTest;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;
import pl.michuu93.prescriptions.drug.model.Refund;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DrugModelTest extends AbstractTest {
    @Test
    public void shouldDeserializeXmlToObject() throws IOException, URISyntaxException, JAXBException {
        DrugsList drugs = getExampleDrugs("drugs.xml");
        assertThat(drugs.getDrugs()).hasSize(51497);

        Optional<Drug> optionalDrug = drugs.getDrugs().stream().filter(d -> d.getBl7().equals("3275422")).findAny();
        assertThat(optionalDrug).isPresent();

        Drug drug = optionalDrug.get();
        assertThat(drug.getEan())
                .isEqualTo("5909990351817");
        assertThat(drug.isPsychotrope())
                .isEqualTo(true);
        assertThat(drug.isSenior())
                .isEqualTo(false);
        assertThat(drug.isVaccine())
                .isEqualTo(false);
        assertThat(drug.getPrice())
                .isEqualTo(35.48f);
        assertThat(drug.getName())
                .isEqualTo("Bunondol");
        assertThat(drug.getInternationalName())
                .isEqualTo("Buprenorphinum");
        assertThat(drug.getForm())
                .isEqualTo("tabl.podj.");
        assertThat(drug.getDose())
                .isEqualTo("0,4 mg");
        assertThat(drug.getPackageSize())
                .isEqualTo("30 tabl. (fiol.)");
        assertThat(drug.getRefunds())
                .extracting(Refund::getLevel)
                .containsOnly("30%", "bezpłatny do limitu");
    }
}