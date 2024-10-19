package com.example.pccoe_oct_2024_hack.Database;


import com.example.pccoe_oct_2024_hack.DTO.User;

public class UserManager extends FirestoreDataManager<User> {
    public UserManager() {
        super("users");  // Pass the collection name "users"
    }
}