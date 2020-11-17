package com.example.mc_bitf17a040_a1.helper_classes;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.Order;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class FileHandler {

    public static void add(AppCompatActivity context, List<String> orders, PersonalDetails personal, CompanyDetails company)
    {
        try
        {
            FileOutputStream file = context.openFileOutput("orders.txt", context.MODE_APPEND);

            for (String item: orders) {

                // Create order
                Order order = new Order(item, personal, company, new Date());

                String line = order.toString();

                file.write(line.getBytes());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Order> get(AppCompatActivity context)
    {
        ArrayList<Order> orderList = new ArrayList<>();
        try
        {
            BufferedReader file = new BufferedReader(new InputStreamReader(context.openFileInput("orders.txt")));
            String line = file.readLine();

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
                line = file.readLine();
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return orderList;
    }
}
