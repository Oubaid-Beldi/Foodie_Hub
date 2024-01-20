package com.tutorial.foodie_hub.CustomerPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tutorial.foodie_hub.CustomerSignUp;
import com.tutorial.foodie_hub.Model.Customer;
import com.tutorial.foodie_hub.R;
import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class Customer_Suggestion extends Fragment {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Customer_Suggestion(String username) {
        this.username = username;
    }
    Button sugBtn,inviteBtn;
    EditText mySug,friendMail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyDataBase myDataBase = new MyDataBase(requireContext());
        View v = inflater.inflate(R.layout.customer_suggestion, null);
        getActivity().setTitle("Suggestion");
        friendMail=v.findViewById(R.id.editTextEmail);
        inviteBtn=v.findViewById(R.id.buttonInvite);
        sugBtn=v.findViewById(R.id.buttonSubmit);
        mySug=v.findViewById(R.id.editTextSuggestion);
        sugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mySugTxt=mySug.getText().toString();
                Customer customer=myDataBase.findCustomerByUsername(getUsername());
                if(mySugTxt.equals("")) {
                    Toast.makeText(requireContext(), "Please enter a suggestion", Toast.LENGTH_SHORT).show();
                }

                else {

                    customer.setSuggestion(mySugTxt);
                    myDataBase.addSugg(customer);
                    Toast.makeText(requireContext(), "Suggestion added successfully", Toast.LENGTH_SHORT).show();


                }


            }
        });
        inviteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mailFriend=friendMail.getText().toString();
                Customer customer=myDataBase.findCustomerByUsername(getUsername());
                if(mailFriend.equals("")) {
                    Toast.makeText(requireContext(), "Please enter Your friend mail ", Toast.LENGTH_SHORT).show();
                }
                else {

                    customer.setNumOfCoupon(customer.getNumOfCoupon());
                    myDataBase.addFriend(customer);
                    Toast.makeText(requireContext(), "You have win a coupon", Toast.LENGTH_SHORT).show();


                }
            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
    }
}
