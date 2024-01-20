package com.tutorial.foodie_hub.dataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tutorial.foodie_hub.Model.Customer;
import com.tutorial.foodie_hub.Model.Dish;

import java.util.ArrayList;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {
    public static final String DBNAME = "login.db";
    public static final String CUSTOMER_TABLE = "customer_table";
    public static final String COLUMN_Customer_ID = "id";
    public static final String COLUMN_Customer_NAME = "name";
    public static final String COLUMN_Customer_PASSWORD = "password";
    public static final String COLUMN_Customer_SUGGESTION = "suggestion";
    public static final String COLUMN_Customer_NUM_OF_COUPON = "num_of_coupon";
    public static final String COLUMN_Customer_EMAIL = "email";

    //dish static
    public static final String DISH_TABLE = "dish_table";
    public static final String COLUMN_DISH_ID = "id";
    public static final String COLUMN_DISH_NAME = "name";
    public static final String COLUMN_DISH_DESCRIPTION = "description";
    public static final String COLUMN_DISH_PRICE = "price";
    public static final String COLUMN_DISH_QUANTITY = "quantity";
    private static final int DATABASE_VERSION = 1;
    // Chef table
    public static final String CHEF_TABLE = "chef_table";
    public static final String COLUMN_CHEF_ID = "id";
    public static final String COLUMN_CHEF_NAME = "name";
    public static final String COLUMN_CHEF_PASSWORD = "password";

    public MyDataBase(@Nullable Context context) {
        super(context, "finalDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCustomerTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + "("
                + COLUMN_Customer_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_Customer_NAME + " TEXT, "
                + COLUMN_Customer_PASSWORD + " TEXT, "
                + COLUMN_Customer_SUGGESTION + " TEXT, "
                + COLUMN_Customer_EMAIL + " TEXT, "
                + COLUMN_Customer_NUM_OF_COUPON + " INTEGER)";
        db.execSQL(createCustomerTableStatement);
        // Create Dish Table
        String createDishTableStatement = "CREATE TABLE " + DISH_TABLE + "("
                + COLUMN_DISH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DISH_NAME + " TEXT, "
                + COLUMN_DISH_DESCRIPTION + " TEXT, "
                + COLUMN_DISH_PRICE + " REAL, "
                + COLUMN_DISH_QUANTITY + " REAL)";
        db.execSQL(createDishTableStatement);
        // Create Chef Table
        String createChefTableStatement = "CREATE TABLE " + CHEF_TABLE + "("
                + COLUMN_CHEF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CHEF_NAME + " TEXT, "
                + COLUMN_CHEF_PASSWORD + " TEXT)";
        db.execSQL(createChefTableStatement);
        insertChef(db, "oubaid", "beldi");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean add(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        
        cv.put(COLUMN_Customer_NAME, customer.getName());
        cv.put(COLUMN_Customer_PASSWORD, customer.getPwd());
        cv.put(COLUMN_Customer_EMAIL, customer.getMail());
        cv.put(COLUMN_Customer_NUM_OF_COUPON,customer.getNumOfCoupon());
        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    // method for addign suggestion to the database
    public boolean addSugg(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_Customer_SUGGESTION, customer.getSuggestion());

        // Construct the WHERE clause to update the specific customer
        String selection = COLUMN_Customer_ID + "=?";
        String[] selectionArgs = {String.valueOf(customer.getId())}; // Assuming getId() returns the customer ID

        // Update the existing customer's suggestion
        int rowsAffected = db.update(CUSTOMER_TABLE, cv, selection, selectionArgs);

        // Check if the update was successful
        if (rowsAffected > 0) {
            return true; // Successfully updated
        } else {
            return false; // Update failed
        }
    }

//    end of method

//    a method for adding a friends via mail and wining coupons
public boolean addFriend(Customer customer) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    // Get the current number of coupons
    int currentNumOfCoupon = customer.getNumOfCoupon();

    // Increment the number of coupons
    currentNumOfCoupon++;

    // Update the ContentValues with the new number of coupons
    cv.put(COLUMN_Customer_NUM_OF_COUPON, currentNumOfCoupon);

    // Construct the WHERE clause to update the specific customer
    String selection = COLUMN_Customer_ID + "=?";
    String[] selectionArgs = {String.valueOf(customer.getId())}; // Assuming getId() returns the customer ID

    // Update the existing customer's numOfCoupon
    int rowsAffected = db.update(CUSTOMER_TABLE, cv, selection, selectionArgs);

    // Check if the update was successful
    if (rowsAffected > 0) {
        return true; // Successfully updated
    } else {
        return false; // Update failed
    }
}



//    find customer by username method
public Customer findCustomerByUsername(String username) {
    SQLiteDatabase db = this.getReadableDatabase();
    Customer customer = null;

    String[] columns = {
            COLUMN_Customer_ID,
            COLUMN_Customer_NAME,
            COLUMN_Customer_PASSWORD,
            COLUMN_Customer_SUGGESTION,
            COLUMN_Customer_EMAIL,
            COLUMN_Customer_NUM_OF_COUPON
    };

    String selection = COLUMN_Customer_NAME + "=?";
    String[] selectionArgs = {username};

    Cursor cursor = db.query(
            CUSTOMER_TABLE,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
    );

    if (cursor.moveToFirst()) {
        @SuppressLint("Range") String customerId = cursor.getString(cursor.getColumnIndex(COLUMN_Customer_ID));
        @SuppressLint("Range") String customerName = cursor.getString(cursor.getColumnIndex(COLUMN_Customer_NAME));
        @SuppressLint("Range") String customerPassword = cursor.getString(cursor.getColumnIndex(COLUMN_Customer_PASSWORD));
        @SuppressLint("Range") String customerSuggestion = cursor.getString(cursor.getColumnIndex(COLUMN_Customer_SUGGESTION));
        @SuppressLint("Range") String customerEmail = cursor.getString(cursor.getColumnIndex(COLUMN_Customer_EMAIL));
        @SuppressLint("Range") int customerNumOfCoupon = cursor.getInt(cursor.getColumnIndex(COLUMN_Customer_NUM_OF_COUPON));

        customer = new Customer(customerName, customerPassword, customerSuggestion, customerEmail, customerNumOfCoupon, customerId);
    }

    cursor.close();
    db.close();

    return customer;
}

    //    ***********************************
    public Boolean CheckUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_Customer_NAME + " = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_Customer_NAME + " = ? AND " + COLUMN_Customer_PASSWORD + " = ?", new String[]{username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean addDish(Dish dish) {
        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(COLUMN_DISH_NAME, dish.getName());
            cv.put(COLUMN_DISH_DESCRIPTION, dish.getDescription());
            cv.put(COLUMN_DISH_PRICE, dish.getPrice());
            cv.put(COLUMN_DISH_QUANTITY, dish.getQuantity());
            long insert = db.insert(DISH_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }

    }


    //chef Code
    private void insertChef(SQLiteDatabase db, String chefName, String chefPassword) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHEF_NAME, chefName);
        values.put(COLUMN_CHEF_PASSWORD, chefPassword);
        db.insert(CHEF_TABLE, null, values);
    }

    public Boolean checkuchefsernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + CHEF_TABLE + " WHERE " + COLUMN_Customer_NAME + " = ? AND " + COLUMN_Customer_PASSWORD + " = ?", new String[]{username, password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public List<Dish> getAllDishes() {
        List<Dish> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + DISH_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                String dishId = cursor.getString(0);
                String dishName = cursor.getString(1);
                String dishDescription = cursor.getString(2);
                double dishPrice = cursor.getDouble(3);
                double dishQuantity = cursor.getDouble(4);

                Dish newDish = new Dish(dishName, dishDescription, dishPrice, dishQuantity, dishId);
                returnList.add(newDish);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }
    public boolean deleteOne(Dish dish){
        SQLiteDatabase db=this.getWritableDatabase();
        String queryString="DELETE FROM " + DISH_TABLE + " WHERE " +COLUMN_DISH_ID +" = " +  dish.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }


}

