package com.tutorial.foodie_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class CustomerLogIn extends AppCompatActivity {
    Button logInBtn;
    EditText username,password;
    MyDataBase db =new MyDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_log_in);
        logInBtn=findViewById(R.id.logInBtn);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(CustomerLogIn.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = db.checkusernamepassword(user, pass);

                    if (checkUserPass) {
                        Toast.makeText(CustomerLogIn.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CustomerLogIn.this, Customer_Botton_Navigation.class);
                        String currentUserNameKey = "CURRENT_USER_NAME_KEY";
                        intent.putExtra(currentUserNameKey , user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CustomerLogIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }


            }
        }});
    }
}
//    startActivity(new Intent(CustomerLogIn.this, Customer_Botton_Navigation.class));