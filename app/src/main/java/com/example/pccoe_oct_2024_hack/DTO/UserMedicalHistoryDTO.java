package com.example.pccoe_oct_2024_hack.DTO;

import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryAdapter;

public class UserMedicalHistoryDTO {

     boolean ischeckd;

     public UserMedicalHistoryDTO(boolean ischeckd){
          this.ischeckd = ischeckd;
     }
     public void setSelected(Boolean b){
          this.ischeckd = ischeckd;

     }
     public boolean isIscheckd(){
          return ischeckd;
     }
}
