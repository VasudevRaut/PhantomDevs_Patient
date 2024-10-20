package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.SlotBookAdapter;
import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.Database.SlotManager;
import com.example.pccoe_oct_2024_hack.Payment.PaymentHelper;
import com.example.pccoe_oct_2024_hack.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SlotBookingPresenter extends AppCompatActivity implements PaymentResultListener {
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

        new SlotManager().getAllData(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                slotList.addAll(queryDocumentSnapshots.toObjects(SlotDTO.class));
                slotAdapter = new SlotBookAdapter(slotList, new SlotBookAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(SlotDTO user) {
                        startPayment();
                    }
                });
                slotRecyclerView.setAdapter(slotAdapter);
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        // Initialize the adapter



    }

    public void startPayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_9gCpkhJrHxTWoT");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Healthशास्त्र");
            options.put("description", "Payment for Appointment");
            options.put("currency", "INR");
            options.put("amount", ""+(20000)); // Amount in paise
            options.put("prefill.email", "vasudevraut156@gmail.com");
            options.put("prefill.contact", "7387579912");

            checkout.open((Activity) SlotBookingPresenter.this, options);

        } catch (Exception e) {
            Toast.makeText(SlotBookingPresenter.this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SlotBookingPresenter.this, UserMedicalHistoryWithCeckboxPresenter.class);
            startActivity(intent);        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(SlotBookingPresenter.this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SlotBookingPresenter.this, UserMedicalHistoryWithCeckboxPresenter.class);
        SlotBookingPresenter.this.startActivity(intent);

    }

    @Override
    public void onPaymentError(int i, String s) {

    }






}