package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mc_bitf17a040_a1.classes.PersonalDetails;

public class PersonalDetailsActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

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

        txtFirstName.setOnClickListener(this);
        txtLastName.setOnClickListener(this);
        txtContact.setOnClickListener(this);
        txtEmail.setOnClickListener(this);

        txtFirstName.setOnFocusChangeListener(this);
        txtLastName.setOnFocusChangeListener(this);
        txtEmail.setOnFocusChangeListener(this);
        txtContact.setOnFocusChangeListener(this);

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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.getId() == R.id.txtFirstName)
        {
            if(hasFocus)
            {
                txtFirstName.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                txtFirstName.setBackgroundResource(R.drawable.texbox_border);
            }
        }
        else if(v.getId() == R.id.txtLastName)
        {
            if(hasFocus)
            {
                txtLastName.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                txtLastName.setBackgroundResource(R.drawable.texbox_border);
            }
        }
        else if(v.getId() == R.id.txtEmail)
        {
            if(hasFocus)
            {
                txtEmail.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                txtEmail.setBackgroundResource(R.drawable.texbox_border);
            }
        }
        else if(v.getId() == R.id.txtContact)
        {
            if(hasFocus)
            {
                txtContact.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                txtContact.setBackgroundResource(R.drawable.texbox_border);
            }
        }
    }
}