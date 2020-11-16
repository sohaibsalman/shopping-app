package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
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
        lstOrders.setOnItemClickListener(this);
        lstOrders.setOnItemLongClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Order order = orders.get(position);
        Intent newIntent = new Intent(this, ConfirmationActivity.class);
        newIntent.putExtra("order", order);

        startActivity(newIntent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        final ListScreenActivity ref = this;
        lstOrders.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        lstOrders.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.main_menu, menu);

                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if(item.getItemId() == R.id.edit)
                {
                    Intent newIntent = new Intent(ref, PersonalDetailsActivity.class);

                    startActivity(newIntent);
                }
                else if(item.getItemId() == R.id.delete)
                {
                    AlertDialog.Builder simple = new AlertDialog.Builder(ref);
                    simple.setTitle("Remove Order");
                    simple.setMessage("Do you really want to delete this order?");
                    simple.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Delete from array list
                            for(int i = 0; i < selectedOrders.size(); i++)
                            {
                                orders.remove(selectedOrders.get(i));
                            }

                            adapterOrders.notifyDataSetChanged();
                        }
                    });
                    simple.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    simple.setCancelable(false);
                    simple.create();
                    simple.show();

                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked)
            {
                if(checked)
                {
                    selectedOrders.add(orders.get(position));
                }
                else
                {
                    selectedOrders.remove(orders.get(position));
                }

                adapterOrders.notifyDataSetChanged();
                mode.setTitle(lstOrders.getCheckedItemCount() + " Selected");
            }

            @Override
            public void onDestroyActionMode(ActionMode mode)
            {
                selectedOrders.clear();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }
        });

        return true;
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

                String orderId = token.nextToken();
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

                Order temp = new Order(orderId, itemName, personalDetails, companyDetails, new Date(date));

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