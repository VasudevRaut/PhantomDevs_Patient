package com.example.pccoe_oct_2024_hack.Database;


import com.example.pccoe_oct_2024_hack.DTO.User;

public class PaymentManager extends FirestoreDataManager<PaymentManager> {
    public PaymentManager() {
        super("users");  // Pass the collection name "users"
    }
}