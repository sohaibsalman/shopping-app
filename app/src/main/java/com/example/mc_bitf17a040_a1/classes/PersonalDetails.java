package com.example.mc_bitf17a040_a1.classes;

import java.io.Serializable;

public class PersonalDetails implements Serializable
{
    private String firstName;
    private String lastName;
    private String email;
    private String contact;

    public PersonalDetails() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.contact = "";
    }

    public PersonalDetails(String firstName, String lastName, String email, String contact)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + "|" + lastName + "|" + email + "|" + contact;
    }
}
