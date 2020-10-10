package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;

public class CompanyDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener, View.OnFocusChangeListener {

    private Spinner spinBoxes;
    private EditText txtCompanyName;
    private EditText txtZip;
    private EditText txtState;
    private EditText txtCity;
    private Button btnNext;
    private Button btnPrev;

    private int selectedBoxIndex;

    String [] boxes = {"N/A", "1-5 boxes", "1-10 boxes", "1-15 boxes", "1-20 boxes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        spinBoxes = (Spinner) findViewById(R.id.spinBoxes);
        txtCompanyName = (EditText) findViewById(R.id.txtCompanyName);
        txtZip = (EditText) findViewById(R.id.txtZip);
        txtState = (EditText) findViewById(R.id.txtState);
        txtCity = (EditText) findViewById(R.id.txtCity);
        btnNext = (Button) findViewById(R.id.btnCompanyNext);
        btnPrev = (Button) findViewById(R.id.btnCompanyPrev);

        txtZip.setOnFocusChangeListener(this);
        txtState.setOnFocusChangeListener(this);
        txtCity.setOnFocusChangeListener(this);
        txtCompanyName.setOnFocusChangeListener(this);

        spinBoxes.setOnItemSelectedListener(this);
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, boxes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinBoxes.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedBoxIndex = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCompanyNext)
        {
            // Get data from previous activity
            PersonalDetails personal = (PersonalDetails) getIntent().getSerializableExtra("PersonalDetails");

            CompanyDetails company = new CompanyDetails(
                    txtCompanyName.getText().toString(),
                    txtZip.getText().toString(),
                    txtState.getText().toString(),
                    txtCity.getText().toString(),
                    boxes[selectedBoxIndex]
            );

            Intent confirmation = new Intent(CompanyDetailsActivity.this, ConfirmationActivity.class);

            confirmation.putExtra("PersonalDetails", personal);
            confirmation.putExtra("CompanyDetails", company);

            startActivity(confirmation);
        }
        else if(v.getId() == R.id.btnCompanyPrev)
        {
            finish();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.getId() == R.id.txtCompanyName)
        {
            if(hasFocus)
            {
                txtCompanyName.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                if(txtCompanyName.getText().toString().trim().equals(""))
                    txtCompanyName.setBackgroundResource(R.drawable.texbox_border);
                else
                    txtCompanyName.setBackgroundResource(R.drawable.textbox_border_green);
            }
        }
        else if(v.getId() == R.id.txtCity)
        {
            if(hasFocus)
            {
                txtCity.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                if(txtCity.getText().toString().trim().equals(""))
                    txtCity.setBackgroundResource(R.drawable.texbox_border);
                else
                    txtCity.setBackgroundResource(R.drawable.textbox_border_green);
            }
        }
        else if(v.getId() == R.id.txtState)
        {
            if(hasFocus)
            {
                txtState.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                if(txtState.getText().toString().trim().equals(""))
                    txtState.setBackgroundResource(R.drawable.texbox_border);
                else
                    txtState.setBackgroundResource(R.drawable.textbox_border_green);
            }
        }
        else if(v.getId() == R.id.txtZip)
        {
            if(hasFocus)
            {
                txtZip.setBackgroundResource(R.drawable.texbox_border_blue);
            }
            else
            {
                if(txtZip.getText().toString().trim().equals(""))
                    txtZip.setBackgroundResource(R.drawable.texbox_border);
                else
                    txtZip.setBackgroundResource(R.drawable.textbox_border_green);
            }
        }
    }
}