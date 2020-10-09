package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CompanyInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String [] boxes = {"N/A", "1-5 boxes", "1-10 boxes", "1-15 boxes", "1-20 boxes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);

        Spinner spin = (Spinner) findViewById(R.id.spinBoxes);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, boxes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spin.setAdapter(adapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}