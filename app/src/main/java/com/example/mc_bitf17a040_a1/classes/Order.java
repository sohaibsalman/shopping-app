package com.example.mc_bitf17a040_a1.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Order implements Serializable
{
    private int id;
    private String guid;
    private String itemName;
    private PersonalDetails personalDetails;
    private CompanyDetails companyDetails;
    private Date dateOfCreation;

    public Order() {
        this.guid = UUID.randomUUID().toString();
        this.itemName = "";
        this.personalDetails = new PersonalDetails();
        this.companyDetails = new CompanyDetails();
        this.dateOfCreation = new Date();
    }
    public Order(String itemName, PersonalDetails personalDetails, CompanyDetails companyDetails, Date dateOfCreation) {
        this.guid = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.personalDetails = personalDetails;
        this.companyDetails = companyDetails;
        this.dateOfCreation = dateOfCreation;
    }

    public Order(String guid, String itemName, PersonalDetails personalDetails, CompanyDetails companyDetails, Date dateOfCreation) {
        this.guid = guid;
        this.itemName = itemName;
        this.personalDetails = personalDetails;
        this.companyDetails = companyDetails;
        this.dateOfCreation = dateOfCreation;
    }

    public Order(int id, String guid, String itemName, PersonalDetails personalDetails, CompanyDetails companyDetails, Date dateOfCreation) {
        this.id = id;
        this.guid = guid;
        this.itemName = itemName;
        this.personalDetails = personalDetails;
        this.companyDetails = companyDetails;
        this.dateOfCreation = dateOfCreation;
    }

    public Order(Order ref)
    {
        this.guid = ref.guid;
        this.itemName = ref.itemName;
        this.personalDetails = ref.personalDetails;
        this.companyDetails = ref.companyDetails;
        this.dateOfCreation = ref.dateOfCreation;
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return guid + "|" + itemName + "|" + personalDetails.toString() + "|" + companyDetails.toString() + "|" + dateOfCreation.toString() + "\n";
    }
}
