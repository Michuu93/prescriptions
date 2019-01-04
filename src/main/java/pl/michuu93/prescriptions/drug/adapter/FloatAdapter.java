package pl.michuu93.prescriptions.drug.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FloatAdapter extends XmlAdapter<String, Float> {
    @Override
    public Float unmarshal(String floatString) {
        return Float.parseFloat(floatString.replace(',', '.'));
    }

    @Override
    public String marshal(Float aFloat) {
        return String.valueOf(aFloat).replace('.', ',');
    }
}