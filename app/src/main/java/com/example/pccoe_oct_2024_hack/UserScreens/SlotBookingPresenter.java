package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pccoe_oct_2024_hack.Adapters.SlotBookAdapter;
import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.R;

import java.util.ArrayList;
import java.util.List;

public class SlotBookingPresenter extends AppCompatActivity {
    private RecyclerView slotRecyclerView;
    private SlotBookAdapter slotAdapter;
    private List<SlotDTO> slotList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_booking_view);
        slotRecyclerView = findViewById(R.id.slotRecyclerView);
        slotRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Simulated data: list of slots (some booked, some available)
        slotList = new ArrayList<>();
        slotList.add(new SlotDTO("10:00 AM - 10:15 AM", false));
        slotList.add(new SlotDTO("10:15 AM - 10:30 AM", true));  // This slot is already booked
        slotList.add(new SlotDTO("10:30 AM - 10:45 AM", false));
        slotList.add(new SlotDTO("10:45 AM - 11:00 AM", false));

        // Initialize the adapter
        slotAdapter = new SlotBookAdapter(slotList, new SlotBookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SlotDTO user) {

            }
        });
        slotRecyclerView.setAdapter(slotAdapter);
    }


}