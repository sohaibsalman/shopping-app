package com.example.mc_bitf17a040_a1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.mc_bitf17a040_a1.classes.Order;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter implements Filterable {
    private Context context;
    private ArrayList<Order> orders;
    private ArrayList<Order> filteredData;
    private ArrayList<Order> allOrders;
    private ArrayList<Order> selectedOrders;

    public ListAdapter(Context context, ArrayList<Order> orders, ArrayList<Order> selectedOrders, ArrayList<Order> allOrders) {
        super(context, R.layout.list_item, orders);
        this.context = context;
        this.orders = orders;
        this.selectedOrders = selectedOrders;
        this.allOrders = allOrders;
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

        if(selectedOrders.contains(order)) {
            listItem.setBackgroundColor(context.getResources().getColor(R.color.colorGray));
        }
        else {
            listItem.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }

        return listItem;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence nameSubStr) {

            FilterResults results = new FilterResults();

            if(filteredData == null)
                filteredData = new ArrayList<>();
            filteredData.clear();

            for(int i = 0; i < orders.size(); i++) {
                filteredData.add(orders.get(i));
            }

            if (nameSubStr != null && nameSubStr.length() > 0) {

                ArrayList<Order> filterList = new ArrayList<Order>();
                for (int i = 0; i < filteredData.size(); i++) {

                    String itemName = filteredData.get(i).getItemName().toUpperCase();
                    nameSubStr = nameSubStr.toString().toUpperCase();

                    if ((itemName).contains(nameSubStr)) {

                        Order s = new Order(filteredData.get(i));
                        filterList.add(s);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            else {
                results.count = allOrders.size();
                results.values = allOrders;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            orders = (ArrayList<Order>)results.values;
        }
    };

}

