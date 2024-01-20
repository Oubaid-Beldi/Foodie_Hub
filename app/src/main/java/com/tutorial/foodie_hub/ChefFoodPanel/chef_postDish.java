package com.tutorial.foodie_hub.ChefFoodPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorial.foodie_hub.Model.Dish;
import com.tutorial.foodie_hub.R;
import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class chef_postDish extends AppCompatActivity {
    Button postBtn;
    EditText dishName, descrp, price, quantity;
    MyDataBase db = new MyDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_post_dish);

        postBtn = findViewById(R.id.postBtn);
        dishName = findViewById(R.id.dishName);
        descrp = findViewById(R.id.descr);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dishNameStr = dishName.getText().toString();
                String descrpStr = descrp.getText().toString();
                String priceStr = price.getText().toString();
                String quantityStr = quantity.getText().toString();

                if (dishNameStr.equals("") || descrpStr.equals("") || priceStr.equals("") || quantityStr.equals("")) {
                    Toast.makeText(chef_postDish.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                } else {
                    double priceValue = Double.parseDouble(priceStr);
                    double quantityValue = Double.parseDouble(quantityStr);

                    // Assuming you have a Dish class constructor that takes dishName, descrp, price, and quantity
                    Dish newDish = new Dish(dishNameStr, descrpStr, priceValue, quantityValue);

                    boolean insert = db.addDish(newDish);

                    if (insert) {
                        Toast.makeText(chef_postDish.this, "Dish posted successfully", Toast.LENGTH_LONG).show();
                        // You can add further actions here, such as navigating to another activity or resetting input fields
                    } else {
                        Toast.makeText(chef_postDish.this, "Posting dish failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}


