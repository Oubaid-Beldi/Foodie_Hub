package com.tutorial.foodie_hub.CustomerPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tutorial.foodie_hub.Model.Dish;
import com.tutorial.foodie_hub.R;
import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class Customer_Menu extends Fragment {
    private ListView lv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.customer_menu, null);
        getActivity().setTitle("Menu");
        lv = v.findViewById(R.id.chefAddedMenu);
        MyDataBase myDataBase = new MyDataBase(requireContext());
        showOnViewList(myDataBase);
//        to delete


        return v;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
    }
    private void showOnViewList(MyDataBase myDataBase) {
        ArrayAdapter<Dish> dishArrayAdapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_list_item_1, myDataBase.getAllDishes());
        lv.setAdapter(dishArrayAdapter);
    }
}
