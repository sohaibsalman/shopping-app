package com.example.mc_bitf17a040_a1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mc_bitf17a040_a1.classes.Order;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Order> orders;
    private ArrayList<Order> selectedOrders;

    public ListAdapter(Context context, ArrayList<Order> orders, ArrayList<Order> selectedOrders) {
        super(context, R.layout.list_item, orders);
        this.context = context;
        this.orders = orders;
        this.selectedOrders = selectedOrders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItem = inflater.inflate(R.layout.list_item, null);

        Order order = orders.get(position);

        ((TextView)listItem.findViewById(R.id.lblItemName)).setText(order.getItemName());

        ((TextView)listItem.findViewById(R.id.lblFirstName)).setText(order.getPersonalDetails().getFirstName());
        ((TextView)listItem.findViewById(R.id.lblLastName)).setText(order.getPersonalDetails().getLastName());
        ((TextView)listItem.findViewById(R.id.lblEmail)).setText(order.getPersonalDetails().getEmail());
        ((TextView)listItem.findViewById(R.id.lblContact)).setText(order.getPersonalDetails().getContact());

        ((TextView)listItem.findViewById(R.id.lblCompanyName)).setText(order.getCompanyDetails().getCompanyName());
        ((TextView)listItem.findViewById(R.id.lblZip)).setText(order.getCompanyDetails().getZip());
        ((TextView)listItem.findViewById(R.id.lblState)).setText(order.getCompanyDetails().getState());
        ((TextView)listItem.findViewById(R.id.lblCity)).setText(order.getCompanyDetails().getCity());
        ((TextView)listItem.findViewById(R.id.lblBoxes)).setText(order.getCompanyDetails().getNoOfBoxes());

        ((TextView)listItem.findViewById(R.id.lblOrderTime)).setText(order.getDateOfCreation().toString());

        return listItem;
    }
}

