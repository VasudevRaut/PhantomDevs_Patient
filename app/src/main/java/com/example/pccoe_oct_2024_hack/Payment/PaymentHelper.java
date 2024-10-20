package com.example.pccoe_oct_2024_hack.Payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.pccoe_oct_2024_hack.UserScreens.UserMedicalHistoryWithCeckboxPresenter;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentHelper implements PaymentResultListener {
    private Context context;

    public PaymentHelper(Context context){
        this.context = context;
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

            checkout.open((Activity) context, options);

        } catch (Exception e) {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, UserMedicalHistoryWithCeckboxPresenter.class);
            context.startActivity(intent);        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, UserMedicalHistoryWithCeckboxPresenter.class);
        context.startActivity(intent);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, UserMedicalHistoryWithCeckboxPresenter.class);
        context.startActivity(intent);
    }
}
