package pl.michuu93.prescriptions.drug.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String booleanString) {
        return Boolean.valueOf(booleanString);
    }

    @Override
    public String marshal(Boolean aBoolean) {
        return aBoolean.toString();
    }
}