package com.example.mc_bitf17a040_a1.classes;

import java.io.Serializable;

public class CompanyDetails implements Serializable
{
    private String companyName;
    private String zip;
    private String state;
    private String city;
    private String noOfBoxes;

    public CompanyDetails() {
        this.companyName = "";
        this.zip = "";
        this.state = "";
        this.city = "";
        this.noOfBoxes = "";
    }

    public CompanyDetails(String companyName, String zip, String state, String city, String noOfBoxes) {
        this.companyName = companyName;
        this.zip = zip;
        this.state = state;
        this.city = city;
        this.noOfBoxes = noOfBoxes;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNoOfBoxes() {
        return noOfBoxes;
    }

    public void setNoOfBoxes(String noOfBoxes) {
        this.noOfBoxes = noOfBoxes;
    }

    @Override
    public String toString() {
        return companyName + "|" + zip + "|" + state + "|" + city+ "|" + noOfBoxes;
    }
}
