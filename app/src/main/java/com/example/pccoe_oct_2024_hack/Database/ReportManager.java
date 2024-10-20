package com.example.pccoe_oct_2024_hack.Database;


import com.example.pccoe_oct_2024_hack.DTO.User;
import com.example.pccoe_oct_2024_hack.DTO.UserMedicalHistoryDTO;

public class ReportManager extends FirestoreDataManager<UserMedicalHistoryDTO> {
    public ReportManager() {
        super("ReportManager");  // Pass the collection name "users"
    }
}