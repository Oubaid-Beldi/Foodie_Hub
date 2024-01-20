package com.tutorial.foodie_hub.ChefFoodPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tutorial.foodie_hub.CustomerPanel.Customer_Cart;
import com.tutorial.foodie_hub.Model.Dish;
import com.tutorial.foodie_hub.R;
import com.tutorial.foodie_hub.dataBase.MyDataBase;

public class ChefHomeFragment extends Fragment {
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chef_home, container, false);
        lv = v.findViewById(R.id.ourmenu);
        setHasOptionsMenu(true); // Enable options menu
        getActivity().setTitle("Home");
        // Assuming you have an instance of MyDataBase
        MyDataBase myDataBase = new MyDataBase(requireContext());
        showOnViewList(myDataBase);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Dish clickedDish= (Dish) adapterView.getItemAtPosition(position);
                myDataBase.deleteOne(clickedDish);
                showOnViewList(myDataBase);
                Toast.makeText(requireContext(),"deleted",Toast.LENGTH_SHORT).show();
            }
        });


//        for the shooping card




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
//