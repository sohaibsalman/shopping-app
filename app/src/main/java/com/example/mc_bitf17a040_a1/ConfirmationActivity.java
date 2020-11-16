package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.Order;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;

public class ConfirmationActivity extends AppCompatActivity {

    TextView lblFullName;
    TextView lblEmail;
    TextView lblContact;
    TextView lblAddress;
    TextView lblBoxes;
    TextView lblCnfrmOrderName;
    TextView lblCnfrmDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        lblFullName = (TextView) findViewById(R.id.lblFullName);
        lblEmail = (TextView) findViewById(R.id.lblEmail);
        lblContact = (TextView) findViewById(R.id.lblContact);
        lblAddress = (TextView) findViewById(R.id.lblAddress);
        lblBoxes = (TextView) findViewById(R.id.lblBoxes);
        lblCnfrmOrderName = (TextView) findViewById(R.id.lblCnfrmOrderName);
        lblCnfrmDate = (TextView) findViewById(R.id.lblCnfrmDate);

        Order order = (Order) getIntent().getSerializableExtra("order");

        PersonalDetails personal = order.getPersonalDetails();
        CompanyDetails company = order.getCompanyDetails();

        lblFullName.setText(personal.getFirstName() + " " + personal.getLastName());
        lblCnfrmOrderName.setText(lblCnfrmOrderName.getText() + " " + order.getItemName());
        lblEmail.setText(lblEmail.getText() + " " + personal.getEmail());
        lblContact.setText(lblContact.getText() + " " + personal.getContact());
        lblAddress.setText(lblAddress.getText() + " " +
                company.getCity() +
                " (" + company.getZip() + "), " +
                company.getState());
        lblCnfrmDate.setText(lblCnfrmDate.getText() + " " + order.getDateOfCreation());
        lblBoxes.setText(lblBoxes.getText() + " " + company.getNoOfBoxes());
    }
}