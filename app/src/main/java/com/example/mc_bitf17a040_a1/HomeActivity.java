package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext;
    private LinearLayout colPaper;
    private LinearLayout colStationary;
    private LinearLayout colDisk;
    private LinearLayout colWaste;
    private ImageView imgPaper;
    private ImageView imgStationary;
    private ImageView imgDisk;
    private ImageView imgWaste;
    private TextView lblPaper;
    private TextView lblStationary;
    private TextView lblDisk;
    private TextView lblWaste;

    private boolean paperSelected;
    private boolean stationarySelected;
    private boolean diskSelected;
    private boolean wasteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnNext = (Button) findViewById(R.id.home_btn_next);

        colPaper = (LinearLayout) findViewById(R.id.colPaper);
        colStationary = (LinearLayout) findViewById(R.id.colStationary);
        colDisk = (LinearLayout) findViewById(R.id.colDisk);
        colWaste = (LinearLayout) findViewById(R.id.colWaste);

        imgPaper = (ImageView) findViewById(R.id.imgPaper);
        imgDisk = (ImageView) findViewById(R.id.imgDisk);
        imgStationary = (ImageView) findViewById(R.id.imgStationary);
        imgWaste = (ImageView) findViewById(R.id.imgWaste);

        lblPaper = (TextView) findViewById(R.id.lblPaper);
        lblDisk = (TextView) findViewById(R.id.lblDisk);
        lblStationary = (TextView) findViewById(R.id.lblStationary);
        lblWaste = (TextView) findViewById(R.id.lblWaste);

        paperSelected = stationarySelected = diskSelected = wasteSelected = false;

        btnNext.setEnabled(false);

        btnNext.setOnClickListener(this);
        colPaper.setOnClickListener(this);
        colStationary.setOnClickListener(this);
        colDisk.setOnClickListener(this);
        colWaste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Resources res = getResources();
        int color = res.getColor(R.color.color_blue);

        if(v.getId() == R.id.home_btn_next)
        {
            Intent newIntent = new Intent(HomeActivity.this, PersonalDetailsActivity.class);

            startActivity(newIntent);
        }
        else if(v.getId() == R.id.colPaper)
        {
            if(!paperSelected)
            {
                colPaper.setBackgroundResource(R.drawable.border_colored);
                imgPaper.setBackgroundResource(R.drawable.ic_paper_colored);
                lblPaper.setTextColor(color);
            }
            else
            {
                colPaper.setBackgroundResource(R.drawable.border);
                imgPaper.setBackgroundResource(R.drawable.ic_paper);
                lblPaper.setTextColor(Color.BLACK);
            }
            // toggle state of selection
            paperSelected = !paperSelected;
        }
        else if(v.getId() == R.id.colStationary)
        {
            if(!stationarySelected)
            {
                colStationary.setBackgroundResource(R.drawable.border_colored);
                imgStationary.setBackgroundResource(R.drawable.ic_stationary_colored);
                lblStationary.setTextColor(color);
            }
            else
            {
                colStationary.setBackgroundResource(R.drawable.border);
                imgStationary.setBackgroundResource(R.drawable.ic_statitionaries);
                lblStationary.setTextColor(Color.BLACK);
            }
            stationarySelected = !stationarySelected;
        }
        else if(v.getId() == R.id.colDisk)
        {
            if(!diskSelected)
            {
                colDisk.setBackgroundResource(R.drawable.border_colored);
                imgDisk.setBackgroundResource(R.drawable.ic_disk_colored);
                lblDisk.setTextColor(color);
            }
            else
            {
                colDisk.setBackgroundResource(R.drawable.border);
                imgDisk.setBackgroundResource(R.drawable.ic_disk);
                lblDisk.setTextColor(Color.BLACK);
            }
            diskSelected = !diskSelected;
        }
        else if(v.getId() == R.id.colWaste)
        {
            if(!wasteSelected)
            {
                colWaste.setBackgroundResource(R.drawable.border_colored);
                imgWaste.setBackgroundResource(R.drawable.ic_waste_colored);
                lblWaste.setTextColor(color);
            }
            else
            {
                colWaste.setBackgroundResource(R.drawable.border);
                imgWaste.setBackgroundResource(R.drawable.ic_waste);
                lblWaste.setTextColor(Color.BLACK);
            }
            wasteSelected = !wasteSelected;
        }

        // toggle next button state
        if(!paperSelected && !wasteSelected && !diskSelected && !stationarySelected)
        {
            btnNext.setEnabled(false);
        }
        else
        {
            btnNext.setEnabled(true);
        }
    }
}