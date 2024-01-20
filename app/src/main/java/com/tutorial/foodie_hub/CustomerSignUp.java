package com.tutorial.foodie_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tutorial.foodie_hub.Model.Customer;
import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class CustomerSignUp extends AppCompatActivity {
    Button login,signup;
    EditText username,password,verifypassword, mail;
    MyDataBase db =new MyDataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);
        login=findViewById(R.id.loginCustomer);
        signup=findViewById(R.id.signUpBtn);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        mail=findViewById(R.id.mail);
        verifypassword=findViewById(R.id.verifypassword);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = verifypassword.getText().toString();
                String c_mail=mail.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(CustomerSignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkUser = db.CheckUsername(user);

                        if (!checkUser) {
                            Customer newCustomer = new Customer();
                            newCustomer.setName(user);
                            newCustomer.setPwd(pass);
                            newCustomer.setMail(c_mail);
                            newCustomer.setNumOfCoupon(0);


                            boolean insert = db.add(newCustomer);

                            if (insert) {
                                Toast.makeText(CustomerSignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CustomerSignUp.this, CustomerLogIn.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CustomerSignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CustomerSignUp.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CustomerSignUp.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //for the logIn
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerSignUp.this,CustomerLogIn.class));
            }
        });

            }
        }




