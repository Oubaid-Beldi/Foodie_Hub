package com.tutorial.foodie_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class ChefLogIn extends AppCompatActivity {
    Button logIn;
    EditText username,password;
    MyDataBase db =new MyDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_log_in);
        logIn = findViewById(R.id.logInBtn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

            logIn.setOnClickListener(new View.OnClickListener() {
         @Override
                public void onClick(View view) {
                    String chefName=username.getText().toString();
                    String chefpwd=password.getText().toString();
             if (chefName.equals("") || chefpwd.equals("")) {
                 Toast.makeText(ChefLogIn.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
             } else {
                 Boolean checkChefPass = db.checkuchefsernamepassword(chefName,chefpwd);

                 if (checkChefPass) {
                     Toast.makeText(ChefLogIn.this, "Welcome back to the kitchen Chef", Toast.LENGTH_LONG).show();
                     Intent intent = new Intent(ChefLogIn.this, Chef_Botton_Navigation.class);
                     startActivity(intent);
                 } else {
                     Toast.makeText(ChefLogIn.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                 }


             }
                }
            });


    }

}