package com.example.pccoe_oct_2024_hack.Database;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseClientToken {
    private String token="";
    public String getFirebaseToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
//                        Log.w(TAG, "Fetching FCM token failed", task.getException());
                        return;
                    }

                    // Get the FCM token
                     token = task.getResult();
                    Log.w("FirebaseToken", token);

                });
        return token;
    }
}
