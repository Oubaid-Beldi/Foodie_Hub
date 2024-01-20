package com.tutorial.foodie_hub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tutorial.foodie_hub.CustomerPanel.Customer_Cart;
import com.tutorial.foodie_hub.CustomerPanel.Customer_Home;
import com.tutorial.foodie_hub.CustomerPanel.Customer_Menu;
import com.tutorial.foodie_hub.CustomerPanel.Customer_Suggestion;


public class Customer_Botton_Navigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_botton_navigation);
        BottomNavigationView navigationView = findViewById(R.id.customerBottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;

        int itemId = item.getItemId();

        if (itemId == R.id.Customerhome) {
            String currentUserNameKey = "CURRENT_USER_NAME_KEY";
            String username = getIntent().getStringExtra(currentUserNameKey);
            fragment = new Customer_Home(username);

        } else if (itemId == R.id.CustomerMenu) {
            fragment = new Customer_Menu();
        } else if (itemId == R.id.Customersuggestion) {
            String currentUserNameKey = "CURRENT_USER_NAME_KEY";
            String username = getIntent().getStringExtra(currentUserNameKey);
            fragment = new Customer_Suggestion(username);
        } else if (itemId == R.id.shopping_cart) {
            fragment = new Customer_Cart();
        }




        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }
}