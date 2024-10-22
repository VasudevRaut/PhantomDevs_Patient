package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.pccoe_oct_2024_hack.MainActivity;
import com.example.pccoe_oct_2024_hack.Norification.UserNotification;
import com.example.pccoe_oct_2024_hack.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
//        listenForNotifications();

        LinearLayout medicalHistory = findViewById(R.id.responces);
        medicalHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, UserMedicalHistoryPresenter.class);
                startActivity(intent);
            }
        });
        LinearLayout scheduleAp = findViewById(R.id.doctorList);
        scheduleAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, DoctorPresenter.class);
                startActivity(intent);
            }
        });

        LinearLayout medical = findViewById(R.id.medicallist);
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, MedicalListPresenter.class);
                startActivity(intent);
            }
        });

        LinearLayout sheduleAppointment = findViewById(R.id.lablist);
        sheduleAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, SheduleAppointmentView.class);
                startActivity(intent);
            }
        });

//        LinearLayout profile = findViewById(R.id.profiles);
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HomeScreen.this, SheduleAppointmentView.class);
//                startActivity(intent);
//            }
//        });

        LinearLayout assistence = findViewById(R.id.assistence);
        assistence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, PersonalisedAssistant.class);
                startActivity(intent);
            }
        });

        LinearLayout recomendatiion = findViewById(R.id.recommendation);
        recomendatiion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, RecognitionView.class);
                startActivity(intent);
            }
        });
    }




}