package com.example.pccoe_oct_2024_hack.UserScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.Adapters.SlotBookAdapter;
import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.PaymentDTO;
import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.Database.DoctorManager;
import com.example.pccoe_oct_2024_hack.Database.SlotManager;
import com.example.pccoe_oct_2024_hack.Payment.PaymentHelper;
import com.example.pccoe_oct_2024_hack.R;
import com.example.pccoe_oct_2024_hack.ShatedPreferences.SharedPrefsHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class SlotBookingPresenter extends AppCompatActivity implements PaymentResultListener {
    private RecyclerView slotRecyclerView;
    private SlotBookAdapter slotAdapter;
    private List<SlotDTO> slotList;
    DoctorDTO user;
    SlotDTO slotDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_booking_view);


        Intent intent = getIntent();

// Retrieve the passed object
         user = (DoctorDTO) intent.getSerializableExtra("doctor");


        slotRecyclerView = findViewById(R.id.slotRecyclerView);
        slotRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Simulated data: list of slots (some booked, some available)
        slotList = new ArrayList<>();

//        new SlotManager().getAllData(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<SlotDTO> ll = createSlotDTOList(user.getTimeSlot());
                slotList.addAll(ll);
                slotAdapter = new SlotBookAdapter(slotList, new SlotBookAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(SlotDTO users,int position) {

                        users.setBooked(false);
                        slotDTO = users;
                        user.getTimeSlot().set(position,0);
                        startPayment();
                    }
                });
                slotRecyclerView.setAdapter(slotAdapter);
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });

        // Initialize the adapter



    }

    public List<SlotDTO> createSlotDTOList(List<Integer> bookingList) {
        List<SlotDTO> slotDTOList = new ArrayList<>();

        // Starting time at 12:00 AM
        int hour = 0;
        int minute = 0;
        Log.w("VVVVVV","vausssssssssssssss");
        // Loop through the bookingList and create SlotDTOs
        for (int i = 0; i < bookingList.size(); i++) {
            Log.w("VVVVVV","vausssssssssssssss");

            // Format the current time range for each half-hour slot
            String timeRange = String.format("%02d:%02d %s - %02d:%02d %s",
                    (hour == 0 ? 12 : hour > 12 ? hour - 12 : hour),
                    minute,
                    (hour < 12) ? "AM" : "PM",
                    (minute == 30 ? hour + 1 : hour) == 0 ? 12 : (minute == 30 ? hour + 1 : hour) > 12 ? (minute == 30 ? hour + 1 : hour) - 12 : (minute == 30 ? hour + 1 : hour),
                    (minute == 0) ? 30 : 0,
                    (minute == 0) ? (hour < 12 ? "AM" : "PM") : ((hour + 1) < 12 ? "AM" : "PM"));

            // Set the isBooked field based on the bookingList value (0 for true, 1 for false)
            boolean isBooked = bookingList.get(i) == 0;

            // Add the SlotDTO object to the list
            slotDTOList.add(new SlotDTO(timeRange, isBooked));

            // Increment the time for the next slot
            if (minute == 0) {
                minute = 30;
            } else {
                minute = 0;
                hour++;
            }
        }

        return slotDTOList;
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
//            Toast.makeText(SlotBookingPresenter.this, "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SlotBookingPresenter.this, UserMedicalHistoryWithCeckboxPresenter.class);
            startActivity(intent);        }
    }


    @Override
    public void onPaymentSuccess(String s) {

        new DoctorManager().addData(user, user.getDocEmail(), new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(this);


        User savedUser = sharedPrefsHelper.getObject("user", User.class);
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = current.format(formatter);
        String reportDate = String.valueOf(System.currentTimeMillis());

        PaymentDTO paymentDTO = new PaymentDTO(reportDate,savedUser.getPatientName(),"Success",user.getCharges());

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore
                .collection("doctors")
                .document(user.getDocEmail())
                .collection("payments")
                .document(reportDate)
                .set(paymentDTO)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SlotBookingPresenter.this, "Sucessssss", Toast.LENGTH_SHORT).show();
//                            Log.d("TAG", "onSuccess: " + doctorModel.getDocEmail());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });







        Toast.makeText(SlotBookingPresenter.this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SlotBookingPresenter.this, UserMedicalHistoryWithCeckboxPresenter.class);
        intent.putExtra("doctor",user);
        intent.putExtra("slotdto",slotDTO);
        startActivity(intent);
//        Toast.makeText(SlotBookingPresenter.this, "fail something", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Fail", Toast.LENGTH_SHORT).show();
    }






}