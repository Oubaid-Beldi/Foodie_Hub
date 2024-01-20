package com.tutorial.foodie_hub.ChefFoodPanel;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.tutorial.foodie_hub.R;

public class ChefDishesFragment extends Fragment {
    Button postDish;
    ConstraintLayout backImg;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chef_dishes,null);
        getActivity().setTitle("Post Dish");
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg1),1500);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg2),2000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg3),3000);


        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        backImg = v.findViewById(R.id.back1);
        backImg.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        postDish =  (Button)v.findViewById(R.id.post_dish);


        postDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),chef_postDish.class));
            }
        });

        return v;
    }
}
