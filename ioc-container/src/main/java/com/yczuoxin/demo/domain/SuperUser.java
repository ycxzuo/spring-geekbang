package com.yczuoxin.demo.domain;

import com.yczuoxin.demo.annotation.Super;

@Super
public class SuperUser extends User {

    private String address;

    private Address workAddress;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                ", workAddress=" + workAddress +
                "} " + super.toString();
    }
}
