package com.tutorial.foodie_hub.CustomerPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tutorial.foodie_hub.Model.Customer;
import com.tutorial.foodie_hub.R;
import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class Customer_Home extends Fragment {
    String username;

    public Customer_Home(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyDataBase myDataBase = new MyDataBase(requireContext());
        View v = inflater.inflate(R.layout.customer_home, container, false);
        getActivity().setTitle("Home");


        Customer customer=myDataBase.findCustomerByUsername(getUsername());
        String welcomeMessage = "Welcome to Foodie Hub, "+ getUsername().toUpperCase()+"! \uD83C\uDF1F We're delighted to have you here, ready to embark on a culinary journey filled with delicious flavors and mouth-watering dishes. Whether you're exploring our menu or placing an order, your satisfaction is our top priority."  ;
        String couponMessage="You have "+customer.getNumOfCoupon()+" Coupon , Invite friends to win more !";

        TextView textViewWelcomeMessage = v.findViewById(R.id.textViewWelcomeMessage);
        TextView textViewCoupon=v.findViewById(R.id.numOfCoupon);
        textViewWelcomeMessage.setText(welcomeMessage);
        textViewCoupon.setText(couponMessage);





        return v;
    }
//

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
    }
}
