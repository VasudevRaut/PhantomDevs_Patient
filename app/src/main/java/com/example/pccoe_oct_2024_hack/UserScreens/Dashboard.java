package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.RegistrationAndLogin.Login;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        LinearLayout medicalHistory = findViewById(R.id.medicalHistory);
        medicalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, UserMedicalHistoryPresenter.class);
                startActivity(intent);
            }
        });
        LinearLayout scheduleAp = findViewById(R.id.scheduleAp);
        scheduleAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, DoctorPresenter.class);
                startActivity(intent);
            }
        });
    }
}