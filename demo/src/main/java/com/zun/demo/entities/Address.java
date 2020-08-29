package com.zun.demo.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String line1,line2,city;

    protected Address(){}

    public Address(String line1, String line2, String city) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
    }
}
