package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.Order;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class ListScreenActivity extends AppCompatActivity implements ListView.OnItemClickListener, ListView.OnItemLongClickListener {

    private ListView lstOrders;
    private ArrayList<Order> orders;
    private ListAdapter adapterOrders;
    private ArrayList<Order> selectedOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        orders = new ArrayList<>();
        selectedOrders = new ArrayList<>();

        adapterOrders = new ListAdapter(this, orders, selectedOrders);

        lstOrders = (ListView)findViewById(R.id.lstOrders);
        lstOrders.setAdapter(adapterOrders);

        initListView();
    }

    private void initListView() {
        ArrayList<Order> tempList = getFileData();

        if(tempList.size() > 0)
        {
            for (Order order: tempList) {
                orders.add(order);
                adapterOrders.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    private ArrayList<Order> getFileData()
    {
        ArrayList<Order> orderList = new ArrayList<>();
        try
        {
            BufferedReader input = new BufferedReader(new InputStreamReader(openFileInput("orders.txt")));
            String line = input.readLine();

            while(line != null)
            {
                StringTokenizer token = new StringTokenizer(line, "|");

                String itemName = token.nextToken();

                // Create personal details object
                PersonalDetails personalDetails = new PersonalDetails(
                        token.nextToken(),
                        token.nextToken(),
                        token.nextToken(),
                        token.nextToken()
                );

                // Create company details object
                CompanyDetails companyDetails = new CompanyDetails(
                        token.nextToken(),
                        token.nextToken(),
                        token.nextToken(),
                        token.nextToken(),
                        token.nextToken()
                );

                // Get Order Date
                String date = token.nextToken();

                Order temp = new Order(itemName, personalDetails, companyDetails, new Date(date));

                orderList.add(temp);

                // Get Next Line
                line = input.readLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return orderList;
    }
}