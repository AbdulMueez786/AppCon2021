package com.example.appcon_2021.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.appcon_2021.Fragments.AllGoals;
import com.example.appcon_2021.Fragments.MyGoals;
import com.example.appcon_2021.Fragments.TopDonors;
import com.example.appcon_2021.R;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    private int selectedTab=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        final LinearLayout homeLayout=findViewById(R.id.home_layout);
        final LinearLayout goalLayout=findViewById(R.id.goal_layout);
        final LinearLayout top_donors_Layout=findViewById(R.id.top_donors_layout);
        final LinearLayout profileLayout=findViewById(R.id.profile_layout);

        final ImageView homeImage =findViewById(R.id.home_image);
        final ImageView goalImage =findViewById(R.id.goal_image);
        final ImageView top_donors_Image =findViewById(R.id.top_donors_image);
        final ImageView profile_Image =findViewById(R.id.profile_image);


        final TextView homeText=findViewById(R.id.home_text);
        final TextView goalText=findViewById(R.id.goal_text);
        final TextView top_donors_Text=findViewById(R.id.top_donors_text);
        final TextView profileText=findViewById(R.id.profile_text);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, AllGoals.class, null).commit();


        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if home is already selected or not
                if(selectedTab!=1){
                    //Set home fragment
                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,AllGoals.class,null).commit();

                    //unselect all other tabs
                    goalText.setVisibility(View.GONE);
                    top_donors_Text.setVisibility(View.GONE);
                    profileText.setVisibility(View.GONE);

                    goalLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    top_donors_Layout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    //select home tab
                    homeText.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.home_selected_icon);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home);

                    // create animation
                    ScaleAnimation scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.setAnimation(scaleAnimation);

                    //set 1st tab as selected
                    selectedTab=1;

                }
            }
        });

        goalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab!=2){

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, MyGoals.class,null).commit();

                    //unselect all other tabs
                    homeText.setVisibility(View.GONE);
                    top_donors_Text.setVisibility(View.GONE);
                    profileText.setVisibility(View.GONE);


                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    top_donors_Layout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    goalText.setVisibility(View.VISIBLE);
                    goalImage.setImageResource(R.drawable.home_selected_icon);
                    goalLayout.setBackgroundResource(R.drawable.round_back_home);

                    // create animation
                    ScaleAnimation  scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    goalLayout.setAnimation(scaleAnimation);


                    selectedTab=2;

                }
            }
        });

        top_donors_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedTab!=3){

                    getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, TopDonors.class,null).commit();

                    //unselect all other tabs
                    homeText.setVisibility(View.GONE);
                    goalText.setVisibility(View.GONE);
                    profileText.setVisibility(View.GONE);


                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    goalLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));


                    top_donors_Text.setVisibility(View.VISIBLE);
                    top_donors_Image.setImageResource(R.drawable.home_selected_icon);
                    top_donors_Layout.setBackgroundResource(R.drawable.round_back_home);

                    // create animation
                    ScaleAnimation  scaleAnimation=new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    top_donors_Layout.setAnimation(scaleAnimation);


                    selectedTab=3;

                }
            }
        });


    }
}