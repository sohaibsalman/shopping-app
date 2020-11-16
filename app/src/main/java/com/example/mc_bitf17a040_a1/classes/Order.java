package com.example.mc_bitf17a040_a1.classes;

import java.util.Date;

public class Order {
    private String itemName;
    private PersonalDetails personalDetails;
    private CompanyDetails companyDetails;
    private Date dateOfCreation;

    public Order() {
        this.itemName = "";
        this.personalDetails = new PersonalDetails();
        this.companyDetails = new CompanyDetails();
        this.dateOfCreation = new Date();
    }
    public Order(String itemName, PersonalDetails personalDetails, CompanyDetails companyDetails, Date dateOfCreation) {
        this.itemName = itemName;
        this.personalDetails = personalDetails;
        this.companyDetails = companyDetails;
        this.dateOfCreation = dateOfCreation;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public CompanyDetails getCompanyDetails() {
        return companyDetails;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return itemName + "|" + personalDetails.toString() + "|" + companyDetails.toString() + "|" + dateOfCreation.toString() + "\n";
    }
}
