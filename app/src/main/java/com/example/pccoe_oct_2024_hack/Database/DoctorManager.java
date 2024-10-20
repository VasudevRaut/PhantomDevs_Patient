package com.example.pccoe_oct_2024_hack.Database;


import com.example.pccoe_oct_2024_hack.DTO.DoctorDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;

public class DoctorManager extends FirestoreDataManager<DoctorDTO> {
    public DoctorManager() {
            super("DoctorDTO");  // Pass the collection name "users"
    }
}