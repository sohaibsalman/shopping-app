package com.example.mc_bitf17a040_a1.helper_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mc_bitf17a040_a1.classes.CompanyDetails;
import com.example.mc_bitf17a040_a1.classes.Order;
import com.example.mc_bitf17a040_a1.classes.PersonalDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shopping-app.db";
    public static final String TABLE_NAME = "orders";
    public static final String ORDER_ID = "orderID";
    public static final String GUID = "guid";
    public static final String ITEM_NAME = "itemName";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String CONTACT = "contact";
    public static final String COMPANY_NAME = "companyName";
    public static final String ZIP = "zip";
    public static final String STATE = "state";
    public static final String CITY = "city";
    public static final String NO_OF_BOXES = "noOfBoxes";
    public static final String ORDER_DATE = "orderDate";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" +
                "orderID INTEGER PRIMARY KEY AUTOINCREMENT, guid TEXT, itemName TEXT, " +
                "firstName TEXT, lastName TEXT, email TEXT, contact TEXT, " +
                "companyName TEXT, zip TEXT, state TEXT, city TEXT, " +
                "noOfBoxes TEXT, orderDate TEXT" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void add(List<String> orders, PersonalDetails personal, CompanyDetails company) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (String item : orders) {

            Order order = new Order(item, personal, company, new Date());

            ContentValues contentValues = new ContentValues();

            contentValues.put(GUID, order.getGuid());
            contentValues.put(ITEM_NAME, order.getItemName());
            contentValues.put(FIRST_NAME, order.getPersonalDetails().getFirstName());
            contentValues.put(LAST_NAME, order.getPersonalDetails().getLastName());
            contentValues.put(EMAIL, order.getPersonalDetails().getEmail());
            contentValues.put(CONTACT, order.getPersonalDetails().getContact());
            contentValues.put(COMPANY_NAME, order.getCompanyDetails().getCompanyName());
            contentValues.put(ZIP, order.getCompanyDetails().getZip());
            contentValues.put(STATE, order.getCompanyDetails().getState());
            contentValues.put(CITY, order.getCompanyDetails().getCity());
            contentValues.put(NO_OF_BOXES, order.getCompanyDetails().getNoOfBoxes());
            contentValues.put(ORDER_DATE, order.getDateOfCreation().toString());

            db.insert(TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<Order> get() {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Order> list = new ArrayList<>();

        String query = "SELECT * FROM orders";

        Cursor result = db.rawQuery(query, null);
        result.moveToFirst();

        for (int i = 0; i < result.getCount(); i++) {

            // Get Order Info
            int id = result.getInt(0);
            String guid = result.getString(1);
            String itemName = result.getString(2);

            // Get Personal Info
            PersonalDetails personalDetails = new PersonalDetails(
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)
            );

            // Get Company Info
            CompanyDetails companyDetails = new CompanyDetails(
                    result.getString(7),
                    result.getString(8),
                    result.getString(9),
                    result.getString(10),
                    result.getString(11)
            );

            // Get order Date
            String date = result.getString(12);

            Order temp = new Order(id, guid, itemName, personalDetails, companyDetails, new Date(date));
            list.add(temp);
            result.moveToNext();
        }

        return list;
    }

    public void delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "orderID = ?", new String[] {id});
    }

    public void update(Order updatedOrder)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String id = Integer.toString(updatedOrder.getId());

        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME, updatedOrder.getItemName());
        contentValues.put(FIRST_NAME, updatedOrder.getPersonalDetails().getFirstName());
        contentValues.put(LAST_NAME, updatedOrder.getPersonalDetails().getLastName());
        contentValues.put(EMAIL, updatedOrder.getPersonalDetails().getEmail());
        contentValues.put(CONTACT, updatedOrder.getPersonalDetails().getContact());
        contentValues.put(COMPANY_NAME, updatedOrder.getCompanyDetails().getCompanyName());
        contentValues.put(ZIP, updatedOrder.getCompanyDetails().getZip());
        contentValues.put(STATE, updatedOrder.getCompanyDetails().getState());
        contentValues.put(CITY, updatedOrder.getCompanyDetails().getCity());
        contentValues.put(NO_OF_BOXES, updatedOrder.getCompanyDetails().getNoOfBoxes());
        contentValues.put(ORDER_DATE, updatedOrder.getDateOfCreation().toString());

        db.update(TABLE_NAME, contentValues, "orderID = ?", new String[] {id});
    }
}
