package com.example.pccoe_oct_2024_hack.SplashScreenSlider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.RegistrationAndLogin.Login;

public class SliderThreePresenter extends AppCompatActivity {

    Button slid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider3);

        slid = findViewById(R.id.slider3);
        slid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SliderThreePresenter.this, Login.class);
                startActivity(i); // invoke the SecondActivity.
            }
        });
    }
}