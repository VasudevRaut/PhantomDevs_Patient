package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryAdapter;
import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryWithCheckBoxAdapter;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.ArrayList;
import java.util.List;

public class UserMedicalHistoryWithCeckboxPresenter extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;

    private CheckBox selectAllCheckBox;
    private RadioGroup selectModeRadioGroup;
    private RadioButton selectReadRadioButton, selectWriteRadioButton;
    private List<UserMedicalHistoryDTO> selectedHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_medical_history_with_ceckbox_presenter);
        searchView = findViewById(R.id.searchView);
        selectedHistory = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        selectAllCheckBox = findViewById(R.id.selectAllCheckBox);
        selectModeRadioGroup = findViewById(R.id.select_all_radio_group);
        selectReadRadioButton = findViewById(R.id.select_all_radio_read);
        selectWriteRadioButton = findViewById(R.id.select_all_radio_write);

        // Set up RecyclerView with a layout manager (e.g., LinearLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<UserMedicalHistoryDTO> userList = new ArrayList<>();
        // Add data to the userList
//        userList.add(new UserMedicalHistoryDTO(false));
//        userList.add(new UserMedicalHistoryDTO(false));
//        userList.add(new UserMedicalHistoryDTO(false));
//        userList.add(new UserMedicalHistoryDTO(false));
//        userList.add(new UserMedicalHistoryDTO(false));
//        userList.add(new UserMedicalHistoryDTO(false));

        UserMedicalHistoryWithCheckBoxAdapter userAdapter = new UserMedicalHistoryWithCheckBoxAdapter(this,userList, new UserMedicalHistoryWithCheckBoxAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserMedicalHistoryDTO user) {

            }
        }, new UserMedicalHistoryWithCheckBoxAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(UserMedicalHistoryDTO userMedicalHistoryDTO, boolean isChecked) {
                if(isChecked){
                    selectedHistory.add(userMedicalHistoryDTO);
                }
                else{
                    selectedHistory.remove(userMedicalHistoryDTO);
                }
//                Toast.makeText(UserMedicalHistoryPresenter.this, ""+isChecked, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(UserMedicalHistoryWithCeckboxPresenter.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle real-time query change
                return false;
            }
        });

        // Handle filter buttons click

        selectAllCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String mode = selectReadRadioButton.isChecked() ? "Read" : "Write";

            if (isChecked) {
                // This line will execute when the checkbox is checked
                userAdapter.selectAllItems(true);
                selectedHistory.addAll(userList);
                Toast.makeText(this, "Come", Toast.LENGTH_SHORT).show();
            } else {
                // This line will execute when the checkbox is unchecked
                userAdapter.selectAllItems(false);
                selectedHistory.clear();
            }
            userAdapter.notifyDataSetChanged();
            // Notify the adapter of data changes
        });

//
//        // Share button logic
        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(v -> {

            Toast.makeText(this, "Slot: "  + " Mode: " , Toast.LENGTH_SHORT).show();

        });


    }
}