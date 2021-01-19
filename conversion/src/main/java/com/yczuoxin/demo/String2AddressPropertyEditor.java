package com.yczuoxin.demo;

import com.yczuoxin.demo.domain.Address;

import java.beans.PropertyEditorSupport;

public class String2AddressPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Address address = new Address();
        String[] split = text.split("-");
        address.setProvince(split[0]);
        if (split.length > 1) {
            address.setCity(split[1]);
        }
        if (split.length > 2) {
            address.setStreet(split[2]);
        }
        setValue(address);
    }
}
