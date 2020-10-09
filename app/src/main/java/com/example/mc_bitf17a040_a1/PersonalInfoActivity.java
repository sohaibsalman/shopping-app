package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private Button btnNext;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtEmail;
    private EditText txtContact;

    private PersonalDetails personalDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        btnNext = (Button) findViewById(R.id.btnPersonalNext);
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtContact = (EditText) findViewById(R.id.txtContact);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPersonalNext)
        {
            personalDetails = new PersonalDetails();

            personalDetails.firstName = txtFirstName.getText().toString();
            personalDetails.lastName = txtLastName.getText().toString();
            personalDetails.email = txtEmail.getText().toString();
            personalDetails.contact = txtContact.getText().toString();

            Intent companyScreen = new Intent(PersonalInfoActivity.this, CompanyInfoActivity.class);

            companyScreen.putExtra("PersonalDetails", (Serializable) personalDetails);

            startActivity(companyScreen);
        }
    }
}