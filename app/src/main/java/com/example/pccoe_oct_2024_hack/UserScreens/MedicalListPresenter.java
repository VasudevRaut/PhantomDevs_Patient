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
import com.example.pccoe_oct_2024_hack.Adapters.MedicalAdapter;
import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.MedicalDTO;
import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.Database.MedicalManager;
import com.example.pccoe_oct_2024_hack.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MedicalListPresenter extends AppCompatActivity {


    private SearchView searchView;
    private RecyclerView recyclerView;
    private Button btnNearest, btnSuggestion, btnReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_list_presenter);




        searchView = findViewById(R.id.searchView2);
        btnNearest = findViewById(R.id.btn_nearest);
        btnSuggestion = findViewById(R.id.btn_suggestion);
        btnReview = findViewById(R.id.btn_review);
//        btnPrice = findViewById(R.id.btn_price);
        recyclerView = findViewById(R.id.recycler_for_leads);

        // Set up RecyclerView with a layout manager (e.g., LinearLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<MedicalDTO> userList = new ArrayList<>();
        // Add data to the userList
        new MedicalManager().getAllData(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                userList.addAll(queryDocumentSnapshots.toObjects(MedicalDTO.class));
                MedicalAdapter userAdapter = new MedicalAdapter(userList, new MedicalAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MedicalDTO user) {
                        Intent intent = new Intent(MedicalListPresenter.this, UplodeDocumentView.class);
                        startActivity(intent);
                    }

                });
                recyclerView.setAdapter(userAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MedicalListPresenter.this));
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
                Toast.makeText(MedicalListPresenter.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
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
//        btnPrice.setOnClickListener(v -> Toast.makeText(this, "Filter: Price", Toast.LENGTH_SHORT).show());
    }



}