package com.example.pccoe_oct_2024_hack.Database;


import com.example.pccoe_oct_2024_hack.DTO.Admin;
import com.example.pccoe_oct_2024_hack.DTO.User;

public class AdminManager extends FirestoreDataManager<Admin> {
    public AdminManager() {
        super("admin");  // Pass the collection name "users"
    }
}