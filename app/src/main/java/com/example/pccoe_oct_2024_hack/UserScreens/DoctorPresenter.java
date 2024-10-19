package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.DoctorAdapter;
import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryAdapter;
import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.ArrayList;
import java.util.List;

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
        userList.add(new DoctorDTO());
        userList.add(new DoctorDTO());
        userList.add(new DoctorDTO());
        userList.add(new DoctorDTO());
        userList.add(new DoctorDTO());
        userList.add(new DoctorDTO());

        DoctorAdapter userAdapter = new DoctorAdapter(userList, new DoctorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DoctorDTO user) {

            }

        });
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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