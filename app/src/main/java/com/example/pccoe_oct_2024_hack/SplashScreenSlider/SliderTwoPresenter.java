package com.example.pccoe_oct_2024_hack.SplashScreenSlider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pccoe_oct_2024_hack.R;

public class SliderTwoPresenter extends AppCompatActivity {

    Button slid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider2);

        slid = findViewById(R.id.slider2);
        slid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SliderTwoPresenter.this, SliderThreePresenter.class);
                startActivity(i); // invoke the SecondActivity.
            }
        });
    }

}