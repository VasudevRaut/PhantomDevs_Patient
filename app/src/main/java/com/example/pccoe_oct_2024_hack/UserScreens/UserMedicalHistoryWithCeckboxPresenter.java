package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryAdapter;
import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryWithCheckBoxAdapter;
import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.SheduleAppointmentDTO;
import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;
import com.example.pccoe_oct_2024_hack.Database.ReportManager;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.ShatedPreferences.SharedPrefsHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserMedicalHistoryWithCeckboxPresenter extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView recyclerView;

    private CheckBox selectAllCheckBox;
    private RadioGroup selectModeRadioGroup;
    private RadioButton selectReadRadioButton, selectWriteRadioButton;
    private List<UserMedicalHistoryDTO> selectedHistory;
    public UserMedicalHistoryWithCheckBoxAdapter userAdapter;
    private DoctorDTO user;
    private SlotDTO slotDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_medical_history_with_ceckbox_presenter);
        Intent intent = getIntent();
        user = (DoctorDTO) intent.getSerializableExtra("doctor");
        slotDTO = (SlotDTO) intent.getSerializableExtra("slotdto");

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

//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setPersistenceEnabled(false)
//                .build();
//        FirebaseFirestore.getInstance().setFirestoreSettings(settings);

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);

        User savedUser = sharedPrefsHelper.getObject("user", User.class);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("patients")
                .document(savedUser.getPatientEmail())
                .collection("reports")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot queryDocumentSnapshots = task.getResult();
                            if (queryDocumentSnapshots != null) {
                                userList.addAll(queryDocumentSnapshots.toObjects(UserMedicalHistoryDTO.class));
                                userAdapter = new UserMedicalHistoryWithCheckBoxAdapter(UserMedicalHistoryWithCeckboxPresenter.this,userList, new UserMedicalHistoryWithCheckBoxAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(UserMedicalHistoryDTO user) {

                                    }
                                }, new UserMedicalHistoryWithCheckBoxAdapter.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(UserMedicalHistoryDTO userMedicalHistoryDTO, boolean isChecked) {
                                        if(isChecked){
//                                            Toast.makeText(UserMedicalHistoryWithCeckboxPresenter.this, "chantd", Toast.LENGTH_SHORT).show();
                                            selectedHistory.add(userMedicalHistoryDTO);
                                        }
                                        else{
                                            selectedHistory.remove(userMedicalHistoryDTO);
                                        }
//                Toast.makeText(UserMedicalHistoryPresenter.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                                    }
                                });

                                recyclerView.setAdapter(userAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(UserMedicalHistoryWithCeckboxPresenter.this));

                            }
                        } else {
                            // Handle the error
                        }
                    }
                });



//        new ReportManager().getAllData(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
//                userList.addAll(queryDocumentSnapshots.toObjects(UserMedicalHistoryDTO.class));
//                userAdapter = new UserMedicalHistoryWithCheckBoxAdapter(UserMedicalHistoryWithCeckboxPresenter.this,userList, new UserMedicalHistoryWithCheckBoxAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(UserMedicalHistoryDTO user) {
//
//                    }
//                }, new UserMedicalHistoryWithCheckBoxAdapter.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(UserMedicalHistoryDTO userMedicalHistoryDTO, boolean isChecked) {
//                        if(isChecked){
//                            Toast.makeText(UserMedicalHistoryWithCeckboxPresenter.this, "chantd", Toast.LENGTH_SHORT).show();
//                            selectedHistory.add(userMedicalHistoryDTO);
//                        }
//                        else{
//                            selectedHistory.remove(userMedicalHistoryDTO);
//                        }
////                Toast.makeText(UserMedicalHistoryPresenter.this, ""+isChecked, Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                recyclerView.setAdapter(userAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(UserMedicalHistoryWithCeckboxPresenter.this));
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });
//        // Set up an adapter for the RecyclerView
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
//                Toast.makeText(this, "Come", Toast.LENGTH_SHORT).show();
            } else {
                // This line will execute when the checkbox is unchecked
                userAdapter.selectAllItems(false);
                selectedHistory.clear();
            }
            userAdapter.notifyDataSetChanged();
            // Notify the adapter of data changes
        });







//        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);

//        User savedUser = sharedPrefsHelper.getObject("user", User.class);


            String s = String.valueOf(Timestamp.now());
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = current.format(formatter);
        String reportDate = String.valueOf(System.currentTimeMillis());
//        // Share button logic
        SheduleAppointmentDTO sheduleAppointmentDTO = new SheduleAppointmentDTO(reportDate,savedUser.getPatientEmail(),user.getDocEmail(),user.getDocName(),savedUser.getPatientName(),formatted,slotDTO.getTimeRange().split("-")[0],slotDTO.getTimeRange().split("-")[1],"waiting","");
        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(v -> {
            //add one entry into the appinment table


            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            firebaseFirestore
                    .collection("doctors")
                    .document(user.getDocEmail())
                    .collection("appointments")
                    .document(reportDate)
                    .set(sheduleAppointmentDTO)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(UserMedicalHistoryWithCeckboxPresenter.this, "Sucessssss", Toast.LENGTH_SHORT).show();
//                            Log.d("TAG", "onSuccess: " + doctorModel.getDocEmail());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });






//            Toast.makeText(this, "Slot: "  + " Mode: " , Toast.LENGTH_SHORT).show();

            for(int i=0;i<selectedHistory.size();i++) {

//                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore
                        .collection("doctors")
                        .document(user.getDocEmail())
                        .collection("reports_"+savedUser.getPatientEmail())
                        .document(reportDate)
                        .set(selectedHistory.get(i))
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UserMedicalHistoryWithCeckboxPresenter.this, "Sucessssss", Toast.LENGTH_SHORT).show();
//                            Log.d("TAG", "onSuccess: " + doctorModel.getDocEmail());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserMedicalHistoryWithCeckboxPresenter.this, "Fail in report"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }



            Intent intent1 = new Intent(UserMedicalHistoryWithCeckboxPresenter.this,HomeScreen.class);
            startActivity(intent1);

        });




        //-------------------------------------------------













    }
}