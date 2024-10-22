package com.example.pccoe_oct_2024_hack.SplashScreenSlider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.Database.UserManager;
import com.example.pccoe_oct_2024_hack.ModelClasses.DoctorModel;
import com.example.pccoe_oct_2024_hack.Norification.UserNotification;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.RegistrationAndLogin.Login;
import com.example.pccoe_oct_2024_hack.ShatedPreferences.SharedPrefsHelper;
import com.example.pccoe_oct_2024_hack.UserScreens.Dashboard;
import com.example.pccoe_oct_2024_hack.UserScreens.HomeScreen;
import com.example.pccoe_oct_2024_hack.UserScreens.SheduleAppointmentView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class Splash_Screen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 1000;
    final String sharedPreferencesFileTitle = "ecoview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        listenForNotifications();
        // Create an instance of SharedPrefsHelper
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);
//        User user = new User("patient5@example.com", "Vasudev Raut", "7387579912", 50, "Male", "O+", 18.5204, 73.8567);
//        sharedPrefsHelper.saveObject("user", user);

        User savedUser = sharedPrefsHelper.getObject("user", User.class);

        if (savedUser != null) {
            String name = savedUser.getPatientName();
        }



        if (sharedPrefsHelper.getObject("user_data", User.class) != null) {
//            User savedUser = sharedPrefsHelper.getObject("user_data", User.class);

            String mobile =" savedUser.getPatientBloodGroup();;";
            String pass = "savedUser.getPatientBloodGroup();";

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
                    Intent i = new Intent(Splash_Screen.this, Login.class);
                    startActivity(i); // invoke the SecondActivity.
                    finish(); // the current activity will get finished.
                }
            }, SPLASH_SCREEN_TIME_OUT);


        }
    }

    private void addData(DoctorModel doctorModel){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore
                .collection("doctors")
                .document(doctorModel.getDocEmail())
                .set(doctorModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: " + doctorModel.getDocEmail());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
        });
}




    private void listenForNotifications() {
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);
//        User user = new User("patient5@example.com", "Vasudev Raut", "7387579912", 50, "Male", "O+", 18.5204, 73.8567);
//        sharedPrefsHelper.saveObject("user", user);

        User savedUser = sharedPrefsHelper.getObject("user", User.class);

        db.collection("patients")
                .document(savedUser.getPatientEmail())
                .collection("appointments")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                        Log.w("FirebaseToken", "");

                        if (e != null) {
                            return;
                        }

                            createAbstractNotification();

                    }
                });

    }

    private void createAbstractNotification() {
        Intent intent = new Intent(this, SheduleAppointmentView.class);
        // Send a notification to the user
        UserNotification userNotification = new UserNotification(this, "user_channel_id", "John Doe");
        userNotification.sendNotification(1, intent);  // Pass notification ID and intent
    }

}