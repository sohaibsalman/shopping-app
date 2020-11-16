package com.example.mc_bitf17a040_a1.classes;

import java.util.Date;
import java.util.UUID;

public class Order {
    private String id;
    private String itemName;
    private PersonalDetails personalDetails;
    private CompanyDetails companyDetails;
    private Date dateOfCreation;

    public Order() {
        this.id = UUID.randomUUID().toString();
        this.itemName = "";
        this.personalDetails = new PersonalDetails();
        this.companyDetails = new CompanyDetails();
        this.dateOfCreation = new Date();
    }
    public Order(String itemName, PersonalDetails personalDetails, CompanyDetails companyDetails, Date dateOfCreation) {
        this.id = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.personalDetails = personalDetails;
        this.companyDetails = companyDetails;
        this.dateOfCreation = dateOfCreation;
    }

    public Order(String id, String itemName, PersonalDetails personalDetails, CompanyDetails companyDetails, Date dateOfCreation) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "|" + itemName + "|" + personalDetails.toString() + "|" + companyDetails.toString() + "|" + dateOfCreation.toString() + "\n";
    }
}
