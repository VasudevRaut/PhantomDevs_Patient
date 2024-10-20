package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.MedicalAdapter;
import com.example.pccoe_oct_2024_hack.DTO.MedicalDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.ArrayList;
import java.util.List;

public class MedicalListSharePresenter extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private Button btnNearest, btnSuggestion, btnReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_list_share_presenter);

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
        userList.add(new MedicalDTO());
        userList.add(new MedicalDTO());
        userList.add(new MedicalDTO());
        userList.add(new MedicalDTO());
        userList.add(new MedicalDTO());
        userList.add(new MedicalDTO());

        MedicalAdapter userAdapter = new MedicalAdapter(userList, new MedicalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MedicalDTO user) {

                showOrderConfirmationDialog();
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
                Toast.makeText(MedicalListSharePresenter.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
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
    }

    private void showOrderConfirmationDialog() {
        // Create an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set dialog title and message
        builder.setTitle("Order Confirmation");
        builder.setMessage("Are you sure you want to confirm your order?");

        // Set positive button (Yes) action
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "Yes", proceed with the order confirmation
                confirmOrder();
            }
        });

        // Set negative button (No) action
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "No", dismiss the dialog and cancel the order
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Function to handle order confirmation (when the user clicks "Yes")
    private void confirmOrder() {
        // Show the order confirmation animation or proceed with the order logic
        // You can trigger the animation or any order-related logic here
        showOrderPlacedAnimation();
    }

    // Example function to show order placed animation (can be customized)
    private void showOrderPlacedAnimation() {
        // Implement your order placed logic or animation here
        // For example, show an image or text that the order is confirmed
        Intent intent = new Intent(MedicalListSharePresenter.this, OrderesPlacedView.class);
        startActivity(intent);
    }
}