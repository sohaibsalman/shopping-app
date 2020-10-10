package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CompanyInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinBoxes;
    private EditText txtCompanyName;
    private EditText txtZip;
    private EditText txtState;
    private EditText txtCity;
    private Button btnNext;
    private Button btnPrev;

    String [] boxes = {"N/A", "1-5 boxes", "1-10 boxes", "1-15 boxes", "1-20 boxes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);

        spinBoxes = (Spinner) findViewById(R.id.spinBoxes);
        txtCompanyName = (EditText) findViewById(R.id.txtCompanyName);
        txtZip = (EditText) findViewById(R.id.txtZip);
        txtState = (EditText) findViewById(R.id.txtState);
        txtCity = (EditText) findViewById(R.id.txtCity);
        btnNext = (Button) findViewById(R.id.btnCompanyNext);
        btnPrev = (Button) findViewById(R.id.btnCompanyPrev);

        spinBoxes.setOnItemSelectedListener(this);
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, boxes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinBoxes.setAdapter(adapter);

        // Get data from previous activity
        PersonalDetails p = (PersonalDetails) getIntent().getSerializableExtra("PersonalDetails");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCompanyNext)
        {

        }
        else if(v.getId() == R.id.btnCompanyPrev)
        {
            finish();
        }
    }
}