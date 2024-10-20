package com.example.pccoe_oct_2024_hack.SplashScreenSlider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.ShatedPreferences.SharedPrefsHelper;
import com.example.pccoe_oct_2024_hack.UserScreens.Dashboard;

public class Splash_Screen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 1000;
    final String sharedPreferencesFileTitle = "ecoview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);


        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);

        if (sharedPrefsHelper.getObject("user_data", User.class) != null) {
            User savedUser = sharedPrefsHelper.getObject("user_data", User.class);

            String mobile = savedUser.getUserEmail();
            String pass = savedUser.getUserEmail();

            if (!mobile.equals("") && !pass.equals("")) {
                Intent intent = new Intent(Splash_Screen.this, Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

        }
        else
        {
            ImageView logoImageView = findViewById(R.id.logoImageView);
            Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
            logoImageView.startAnimation(scaleAnimation);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Intent is used to switch from one activity to another.
                    Intent i = new Intent(Splash_Screen.this, SliderOnePresenter.class);
                    startActivity(i); // invoke the SecondActivity.
                    finish(); // the current activity will get finished.
                }
            }, SPLASH_SCREEN_TIME_OUT);


        }
    }
}