package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.Order;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;
import com.example.mc_bitf17a040_a1.helper_classes.FileHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener, View.OnFocusChangeListener {

    private static final int SEND_SMS_PERMISSION_REQUEST_CODE = 1 ;
    private Spinner spinBoxes;
    private EditText txtCompanyName;
    private EditText txtZip;
    private EditText txtState;
    private EditText txtCity;
    private Button btnNext;
    private Button btnPrev;

    private int selectedBoxIndex;
    private PersonalDetails personal;
    private List<String> orders;

    private boolean isForEdit;

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

        // Get data from previous activity
        personal = (PersonalDetails) getIntent().getSerializableExtra("PersonalDetails");
        orders = (List<String>) getIntent().getSerializableExtra("itemsList");

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
    protected void onResume() {
        super.onResume();

        isForEdit = (boolean) getIntent().getBooleanExtra("isForEdit", false);

        if(isForEdit)
        {
            Order details = (Order) getIntent().getSerializableExtra("SelectedOrder");

            if(details != null)
            {
                txtCompanyName.setText(details.getCompanyDetails().getCompanyName());
                txtZip.setText(details.getCompanyDetails().getZip());
                txtState.setText(details.getCompanyDetails().getState());
                txtCity.setText(details.getCompanyDetails().getCity());
            }
        }
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
            if(txtCompanyName.getText().toString().trim().equals("") ||
            txtCity.getText().toString().trim().equals("") ||
            txtState.getText().toString().trim().equals("") ||
            txtZip.getText().toString().trim().equals("") ||
            selectedBoxIndex == 0)
            {
                Toast.makeText(getApplicationContext(), "Please enter all fields first", Toast.LENGTH_SHORT).show();
                return;
            }

            CompanyDetails company = new CompanyDetails(
                    txtCompanyName.getText().toString(),
                    txtZip.getText().toString(),
                    txtState.getText().toString(),
                    txtCity.getText().toString(),
                    boxes[selectedBoxIndex]
            );

            if(!isForEdit)
            {
                // Add to order.txt
                FileHandler.add(this, orders, personal, company);
            }
            else
            {
                ArrayList<Order> orders = (ArrayList<Order>) getIntent().getSerializableExtra("Orders");
                Order selected = (Order) getIntent().getSerializableExtra("SelectedOrder");

                int index = -1;

                for(int i = 0; i < orders.size(); i++)
                {
                    Order temp = orders.get(i);
                    if(temp.getId().equals(selected.getId()))
                    {
                        index = i;
                        break;
                    }
                }

                Order updatedOrder = orders.get(index);
                updatedOrder.setCompanyDetails(company);

                orders.set(index, updatedOrder);

                FileHandler.update(this, orders);
            }

            sendNotifications();

            Intent newIntent = new Intent(CompanyDetailsActivity.this, ListScreenActivity.class);
            startActivity(newIntent);
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

    private boolean sendNotifications()
    {
        // Show notification in notification bar
        showNotification();

        // Send SMS Notification
        sendSMS();

        // Send email Notification
        sendEmail();

        return true;
    }

    private void sendEmail()
    {
        String emailAddress = personal.getEmail().trim();
        String message = "Dear " + personal.getFirstName() + ", your order has been placed successfully. Thank you for choosing us!";

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        i.putExtra(Intent.EXTRA_SUBJECT, "Shopping App - Order Placed");
        i.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
            finish();
        }
        catch (Exception ex) {
        }

    }

    private void sendSMS() {
        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            String message = "Dear " + personal.getFirstName() + ", your order has been placed successfully. Thank you for choosing us!";
            // String destination = personal.getContact().trim();
            String destination = "03164141068"; 

            // Permission Granted, send sms
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(destination, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_LONG).show();
        }
        else
        {
            // Request for permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }

    private void showNotification() {
        String message = "Dear Customer, your order has been placed!";
        Intent i = new Intent(this, ListScreenActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.app_name))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle("Shopping App")
                .setContentText(message)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }


    private boolean checkPermission(String permission)
    {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}