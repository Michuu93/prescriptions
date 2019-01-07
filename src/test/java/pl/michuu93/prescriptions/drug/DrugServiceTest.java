package pl.michuu93.prescriptions.drug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.michuu93.prescriptions.AbstractTest;
import pl.michuu93.prescriptions.drug.model.Drug;
import pl.michuu93.prescriptions.drug.model.DrugsList;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DrugServiceTest extends AbstractTest {
    @Test
    @Transactional
    public void shouldSaveAndFind() throws JAXBException, IOException, URISyntaxException {
        DrugsList drugs = getExampleDrugs("example_drug.xml");
        assertThat(drugs).isNotNull();
        assertThat(drugs.getDrugs()).hasSize(1);
        DrugsUpdateResponse drugsUpdateResponse = updateDrugs(drugs.getDrugs());

        assertThat(drugsUpdateResponse).isNotNull();
        assertThat(drugsUpdateResponse.getActive()).isEqualTo(1);
        assertThat(drugsUpdateResponse.getInactivated()).isEqualTo(0);

        Drug drug = drugs.getDrugs().get(0);
        Optional<Drug> optionalDrugFromDb = drugService.getDrugByBl7(drug.getBl7());
        assertThat(optionalDrugFromDb).isPresent();
        assertThat(optionalDrugFromDb.get()).isEqualTo(drug);
    }
}