package com.example.pccoe_oct_2024_hack.RegistrationAndLogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.Database.UserManager;
import com.example.pccoe_oct_2024_hack.LocationHelper.GetLocation;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.ShatedPreferences.SharedPrefsHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    double longi;
    double lati;
    String token;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    private ImageView logoImageView;
    private TextView titleTextView;
    private TextInputEditText firstNameEditText, lastNameEditText, emailEditText, numberEditText, passwordEditText, confirmPasswordEditText;
    private TextInputEditText ageEditText, genderEditText, bloodGroupEditText, educationEditText, professionEditText, hospitalNameEditText, addressEditText, chargesEditText;
    private CheckBox agreeCheckBox;
    private Button createAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        firstNameEditText = findViewById(R.id.first_name);  // First Name
//        lastNameEditText = findViewById(R.id.last_name);  // Last Name
        emailEditText = findViewById(R.id.email);  // Email
        numberEditText = findViewById(R.id.number);  // Contact Number
        passwordEditText = findViewById(R.id.password);  // Password
        confirmPasswordEditText = findViewById(R.id.confirm_password);  // Confirm Password

        ageEditText = findViewById(R.id.age);  // Age
        genderEditText = findViewById(R.id.Gender);  // Gender
        bloodGroupEditText = findViewById(R.id.bludGroup);  // Blood Group

 // Charges

        agreeCheckBox = findViewById(R.id.agreeCheckBox);  // Terms & Conditions CheckBox

        createAccountButton = findViewById(R.id.create_account);  //
        List<Double> location = new GetLocation(SignUp.this).getCurrentLocation();

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = 0.0;
                double longi = 0.0;
                if(!location.isEmpty()){
                    lat = location.get(0);
                    longi = location.get(1);
                }
                User user =  new User(emailEditText.getText().toString(),firstNameEditText.getText().toString(),numberEditText.getText().toString(), 20,genderEditText.getText().toString(),bloodGroupEditText.getText().toString(),lat,longi);


                SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(SignUp.this);
//                User user = new User("patient5@example.com", "Vasudev Raut", "7387579     912", 50, "Male", "O+", 18.5204, 73.8567);
                sharedPrefsHelper.saveObject("user", user);

//                User savedUser = sharedPrefsHelper.getObject("user", User.class);


                new UserManager().addData(user, user.getPatientEmail(), new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(SignUp.this, "Register successfully", Toast.LENGTH_SHORT).show();
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
            }
        });




    }}



