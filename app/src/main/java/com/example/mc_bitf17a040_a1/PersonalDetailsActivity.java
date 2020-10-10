package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mc_bitf17a040_a1.classes.PersonalDetails;

public class PersonalDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext;
    private Button btnPrev;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtEmail;
    private EditText txtContact;

    private PersonalDetails personalDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        btnNext = (Button) findViewById(R.id.btnPersonalNext);
        btnPrev = (Button) findViewById(R.id.btnPersonalPrev);

        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtContact = (EditText) findViewById(R.id.txtContact);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPersonalNext)
        {
            personalDetails = new PersonalDetails(
                    txtFirstName.getText().toString(),
                    txtLastName.getText().toString(),
                    txtEmail.getText().toString(),
                    txtContact.getText().toString()
            );

            Intent companyScreen = new Intent(this, CompanyDetailsActivity.class);

            companyScreen.putExtra("PersonalDetails", personalDetails);

            startActivity(companyScreen);
        }
        else if(v.getId() == R.id.btnPersonalPrev)
        {
            finish();
        }
    }
}