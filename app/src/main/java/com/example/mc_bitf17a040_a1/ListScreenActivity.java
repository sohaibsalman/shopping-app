package com.example.mc_bitf17a040_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.Order;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;
import com.example.mc_bitf17a040_a1.helper_classes.DBHandler;
import com.example.mc_bitf17a040_a1.helper_classes.FileHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class ListScreenActivity extends AppCompatActivity implements
        View.OnClickListener,
        ListView.OnItemClickListener,
        ListView.OnItemLongClickListener, SearchView.OnQueryTextListener {

    private ArrayList<Order> orders;
    private ArrayList<Order> selectedOrders;
    private ArrayList<Order> allOrders;

    private ListAdapter adapterOrders;

    private ListView lstOrders;
    private SearchView searchViewOrders;
    private MenuItem itemEdit;
    private MenuItem itemDelete;
    private FloatingActionButton btnFloatOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        orders = new ArrayList<>();
        selectedOrders = new ArrayList<>();
        allOrders = new ArrayList<>();

        adapterOrders = new ListAdapter(this, orders, selectedOrders, allOrders);

        lstOrders = (ListView)findViewById(R.id.lstOrders);
        searchViewOrders = (SearchView) findViewById(R.id.searchViewOrders);
        btnFloatOrder = (FloatingActionButton) findViewById(R.id.btnFloatOrder);

        lstOrders.setAdapter(adapterOrders);

        initListView();

        lstOrders.setOnItemClickListener(this);
        lstOrders.setOnItemLongClickListener(this);
        searchViewOrders.setOnQueryTextListener(this);
        btnFloatOrder.setOnClickListener(this);
    }

    /********************************
     Listeners
     ********************************/

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.btnFloatOrder)
        {
            Intent newIntent = new Intent(this, OrderItemsActivity.class);
            startActivity(newIntent);
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
                itemEdit = menu.getItem(0);
                itemDelete = menu.getItem(1);
                return true;
            }

            @Override
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
                if(item.getItemId() == R.id.edit)
                {
                    Intent newIntent = new Intent(ref, PersonalDetailsActivity.class);

                    newIntent.putExtra("EditOrder", true);
                    newIntent.putExtra("Orders", orders);
                    newIntent.putExtra("SelectedOrder", selectedOrders.get(0));


                    startActivity(newIntent);
                }
                else if(item.getItemId() == R.id.delete)
                {
                    AlertDialog.Builder simple = new AlertDialog.Builder(ref);
                    simple.setTitle("Remove Order(s)");
                    simple.setMessage("Do you really want to delete the selected order(s)?");
                    simple.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteOrder(ref);

                            adapterOrders.notifyDataSetChanged();

                            // Display Toast Notification
                            int count = lstOrders.getCheckedItemCount();
                            Toast.makeText(ref, count + " order(s) deleted", Toast.LENGTH_SHORT).show();

                            mode.finish();
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


                if(lstOrders.getCheckedItemCount() > 1)
                    itemEdit.setEnabled(false);
                else
                    itemEdit.setEnabled(true);

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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapterOrders.getFilter().filter(newText);
        adapterOrders.notifyDataSetChanged();
        return false;
    }

    /********************************
     Utility functions
     ********************************/

    private void initListView() {
        DBHandler db = new DBHandler(this);

        ArrayList<Order> tempList = db.get();

        if(tempList.size() > 0)
        {
            for (Order order: tempList) {
                orders.add(order);
                allOrders.add(order);
                adapterOrders.notifyDataSetChanged();
            }
        }
    }

    private void deleteOrder(AppCompatActivity context) {
        DBHandler db = new DBHandler(this);
        // Delete from array list
        for (int i = 0; i < selectedOrders.size(); i++) {
            Order removedOrder = selectedOrders.get(i);
            orders.remove(removedOrder);

            // Delete from DB
            db.delete(Integer.toString(removedOrder.getId()));
        }

        selectedOrders.clear();
    }

}   // end of class