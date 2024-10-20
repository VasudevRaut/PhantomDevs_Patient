package com.example.pccoe_oct_2024_hack.Database;


import com.example.pccoe_oct_2024_hack.DTO.MedicalDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;

public class MedicalManager extends FirestoreDataManager<MedicalDTO> {
    public MedicalManager() {
        super("MedicalDTO");  // Pass the collection name "users"
    }
}