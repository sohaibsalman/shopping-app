package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext;
    private Button btnOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnOrders = (Button) findViewById(R.id.btnOrders);

        btnNext.setOnClickListener(this);
        btnOrders.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnNext) {
            Intent newActivity = new Intent(MainActivity.this, OrderItemsActivity.class);

            startActivity(newActivity);
        }
        else if(v.getId() == R.id.btnOrders)
        {
            Intent newActivity = new Intent(MainActivity.this, ListScreenActivity.class);

            startActivity(newActivity);
        }
    }
}