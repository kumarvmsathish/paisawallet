package com.vivartha.kryptopal.model;

/**
 * Created by ANANTH on 21-09-2017.
 */

public class Contact {

    private Integer personImage;
    private String personName;
    private String country;
    private String addedDate;

    public Integer getPersonImage() {
        return personImage;
    }

    public void setPersonImage(Integer personImage) {
        this.personImage = personImage;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
}
