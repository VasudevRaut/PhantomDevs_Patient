package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.DoctorAdapter;
import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryAdapter;
import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.Database.DoctorManager;
import com.example.pccoe_oct_2024_hack.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorPresenter extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private Button btnNearest, btnSuggestion, btnReview, btnPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);


        searchView = findViewById(R.id.searchView);
        btnNearest = findViewById(R.id.btn_nearest);
        btnSuggestion = findViewById(R.id.btn_suggestion);
        btnReview = findViewById(R.id.btn_review);
        btnPrice = findViewById(R.id.btn_price);
        recyclerView = findViewById(R.id.recyclerView);

        // Set up RecyclerView with a layout manager (e.g., LinearLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<DoctorDTO> userList = new ArrayList<>();
        // Add data to the userList
        new DoctorManager().getAllData(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                userList.addAll(queryDocumentSnapshots.toObjects(DoctorDTO.class));
                DoctorAdapter userAdapter = new DoctorAdapter(DoctorPresenter.this,userList, new DoctorAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DoctorDTO user) {
                        Intent intent = new Intent(DoctorPresenter.this, SlotBookingPresenter.class);
                        intent.putExtra("doctor",user);
                        startActivity(intent);
                    }

                });
                recyclerView.setAdapter(userAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DoctorPresenter.this));
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        // Set up an adapter for the RecyclerView
        // recyclerView.setAdapter(yourAdapter);

        // Handle SearchView input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query
                Toast.makeText(DoctorPresenter.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle real-time query change
                return false;
            }
        });

        // Handle filter buttons click
        btnNearest.setOnClickListener(v -> Toast.makeText(this, "Filter: Nearest", Toast.LENGTH_SHORT).show());
        btnSuggestion.setOnClickListener(v -> Toast.makeText(this, "Filter: Suggestion", Toast.LENGTH_SHORT).show());
        btnReview.setOnClickListener(v -> Toast.makeText(this, "Filter: Review", Toast.LENGTH_SHORT).show());
        btnPrice.setOnClickListener(v -> Toast.makeText(this, "Filter: Price", Toast.LENGTH_SHORT).show());
    }


}