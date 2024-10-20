package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pccoe_oct_2024_hack.Adapters.SheduleAppointmentAdapter;
import com.example.pccoe_oct_2024_hack.Adapters.UserAdapter;
import com.example.pccoe_oct_2024_hack.DTO.SheduleAppointmentDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.R;

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
        userList.add(new SheduleAppointmentDTO());
        userList.add(new SheduleAppointmentDTO());
        userList.add(new SheduleAppointmentDTO());
        userList.add(new SheduleAppointmentDTO());
        userList.add(new SheduleAppointmentDTO());
        userList.add(new SheduleAppointmentDTO());

        SheduleAppointmentAdapter userAdapter = new SheduleAppointmentAdapter(userList, new SheduleAppointmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SheduleAppointmentDTO user) {

            }
        });
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}