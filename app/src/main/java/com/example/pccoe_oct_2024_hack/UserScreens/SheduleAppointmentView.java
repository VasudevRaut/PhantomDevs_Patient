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


        try {
            SheduleAppointmentDTO appointment1 = new SheduleAppointmentDTO(
                    "Dr. John Doe",
                    "123 Medical Lane, Springfield",
                    45,
                    500.0,
                    "General Practitioner",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2024-11-15 10:30"),
                    "Online"
            );
            userList.add(appointment1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

// Dummy data object 2
        try {
            SheduleAppointmentDTO appointment2 = new SheduleAppointmentDTO(
                    "Dr. Sarah Smith",
                    "456 Health Ave, Shelbyville",
                    38,
                    750.0,
                    "Cardiologist",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2024-12-01 14:00"),
                    "Offline"

            );
            userList.add(appointment2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }



        SheduleAppointmentAdapter userAdapter = new SheduleAppointmentAdapter(this,userList, new SheduleAppointmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SheduleAppointmentDTO user) {

            }
        });
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}