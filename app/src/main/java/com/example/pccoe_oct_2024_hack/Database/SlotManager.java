package com.example.pccoe_oct_2024_hack.Database;


import com.example.pccoe_oct_2024_hack.DTO.SlotDTO;
import com.example.pccoe_oct_2024_hack.DTO.User;

public class SlotManager extends FirestoreDataManager<SlotDTO> {
    public SlotManager() {
        super("SlotDTO");  // Pass the collection name "users"
    }
}