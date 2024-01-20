package com.tutorial.foodie_hub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tutorial.foodie_hub.ChefFoodPanel.ChefDishesFragment;
import com.tutorial.foodie_hub.ChefFoodPanel.ChefHomeFragment;
import com.tutorial.foodie_hub.ChefFoodPanel.ChefOrderFragment;
import com.tutorial.foodie_hub.ChefFoodPanel.ChefPendingOrdersFragment;

public class Chef_Botton_Navigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_button_navigation);
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;

        int itemId = item.getItemId();

        if (itemId == R.id.Chefhome) {
            fragment = new ChefHomeFragment();
        } else if (itemId == R.id.pending_order) {
            fragment = new ChefPendingOrdersFragment();
        } else if (itemId == R.id.orders) {
            fragment = new ChefOrderFragment();
        } else if (itemId == R.id.dishes) {
            fragment = new ChefDishesFragment();
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
