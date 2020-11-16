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
import android.widget.SearchView;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.Order;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;
import com.example.mc_bitf17a040_a1.helper_classes.FileHandler;

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

public class ListScreenActivity extends AppCompatActivity implements ListView.OnItemClickListener, ListView.OnItemLongClickListener {

    private ListView lstOrders;
    private SearchView searchViewOrders;
    private ArrayList<Order> orders;
    private ListAdapter adapterOrders;
    private ArrayList<Order> selectedOrders;

    private MenuItem itemEdit;
    private MenuItem itemDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);

        orders = new ArrayList<>();
        selectedOrders = new ArrayList<>();

        adapterOrders = new ListAdapter(this, orders, selectedOrders);

        lstOrders = (ListView)findViewById(R.id.lstOrders);
        searchViewOrders = (SearchView) findViewById(R.id.searchViewOrders);

        lstOrders.setAdapter(adapterOrders);

        initListView();
        lstOrders.setOnItemClickListener(this);
        lstOrders.setOnItemLongClickListener(this);
    }

    private void initListView() {
        ArrayList<Order> tempList = FileHandler.get(this);

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
                itemEdit = menu.getItem(0);
                itemDelete = menu.getItem(1);
                return true;
            }

            @Override
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
                if(item.getItemId() == R.id.edit)
                {
                    Intent newIntent = new Intent(ref, PersonalDetailsActivity.class);
                    newIntent.putExtra("Orders", selectedOrders.get(0));

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

    private void deleteOrder(AppCompatActivity context)
    {
        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput("orders.txt")));
//            FileOutputStream writer = context.openFileOutput("temp.txt", context.MODE_WORLD_READABLE);

            for(int i = 0; i < selectedOrders.size(); i++)
            {
                Order removedOrder = selectedOrders.get(i);
                // Delete from array list
                orders.remove(removedOrder);

//                // Delete from file
//                String line = reader.readLine();
//
//                while(line != null)
//                {
//                    StringTokenizer token = new StringTokenizer(line, "|");
//                    String idInFile = token.nextToken();
//
//                    if(!removedOrder.getId().equals(idInFile))
//                    {
//                        writer.write(line.getBytes());
//                    }
//                    line = reader.readLine();
//                }
            }
//            reader.close();
//            writer.close();
//
//            // Delete old file
//            File file = new File(getFilesDir(), "orders.txt");
//            file.delete();
//
//            // Update the name of new file
//            file = new File(getFilesDir(), "temp.txt");
//            file.renameTo(new File("orders.txt"));

            selectedOrders.clear();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}