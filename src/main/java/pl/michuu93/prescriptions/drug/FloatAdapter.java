package pl.michuu93.prescriptions.drug;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FloatAdapter extends XmlAdapter<String, Float> {
    @Override
    public Float unmarshal(String floatString) {
        var replacedString = floatString.replace(',', '.');
        return Float.parseFloat(replacedString);
    }

    @Override
    public String marshal(Float aFloat) {
        var floatString = String.valueOf(aFloat);
        return floatString.replace('.', ',');
    }
}