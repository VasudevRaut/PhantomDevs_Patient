package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pccoe_oct_2024_hack.Adapters.SheduleAppointmentAdapter;
import com.example.pccoe_oct_2024_hack.Adapters.UserAdapter;
import com.example.pccoe_oct_2024_hack.DTO.SheduleAppointmentDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.ShatedPreferences.SharedPrefsHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SheduleAppointmentView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_appointment_view);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewshedule);
        List<SheduleAppointmentDTO> userList = new ArrayList<>();
        // Add data to the userList


        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);

        User savedUser = sharedPrefsHelper.getObject("user", User.class);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("patients")
                .document(savedUser.getPatientEmail())
                .collection("appointments")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot queryDocumentSnapshots = task.getResult();
                            if (queryDocumentSnapshots != null) {
                                userList.addAll(queryDocumentSnapshots.toObjects(SheduleAppointmentDTO.class));
                                SheduleAppointmentAdapter userAdapter = new SheduleAppointmentAdapter(SheduleAppointmentView.this,userList, new SheduleAppointmentAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(SheduleAppointmentDTO user) {

                                    }
                                });
                                recyclerView.setAdapter(userAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(SheduleAppointmentView.this));
                            }
                        } else {
                            // Handle the error
                        }
                    }
                });




    }
}