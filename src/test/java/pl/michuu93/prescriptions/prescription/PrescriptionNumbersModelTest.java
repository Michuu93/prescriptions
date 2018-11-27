package pl.michuu93.prescriptions.prescription;

import org.junit.Test;
import pl.michuu93.prescriptions.AbstractTest;
import pl.michuu93.prescriptions.prescription.model.PrescriptionCategory;
import pl.michuu93.prescriptions.prescription.model.PrescriptionNumberList;
import pl.michuu93.prescriptions.prescription.model.PrescriptionType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PrescriptionNumbersModelTest extends AbstractTest {
    @Test
    public void shouldDeserializeXmlToObject() throws IOException, URISyntaxException, JAXBException {
        String exampleXml = loadExample("examplePrescriptionNumbers.xml");
        var context = JAXBContext.newInstance(PrescriptionNumberList.class);
        var marshaller = context.createUnmarshaller();
        PrescriptionNumberList prescriptionNumbers = (PrescriptionNumberList) marshaller.unmarshal(new StringReader(exampleXml));

        assertThat(prescriptionNumbers.getCategory()).isEqualTo(PrescriptionCategory.Rpw);
        assertThat(prescriptionNumbers.getType()).isEqualTo(PrescriptionType.S);
        assertThat(prescriptionNumbers.getNumbers()).hasSize(2);
        assertThat(prescriptionNumbers.getNumbers())
                .containsExactlyInAnyOrder("15035300000002015078", "15035300000002015178");
    }
}