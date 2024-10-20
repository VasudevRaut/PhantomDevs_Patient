package com.example.pccoe_oct_2024_hack.DTO;

import com.example.pccoe_oct_2024_hack.Adapters.UserMedicalHistoryAdapter;

import java.util.List;

public class UserMedicalHistoryDTO {

     boolean ischeckd;

     private String reportID;
     private String docID; //change to docEmail
     private String patientID; //change to patientEmail

     private String description;
     private String dataString;
     private List<String> symptoms;
     private List<String> medicines;
     private List<String> measures;
     private String image;
     private String PDF;

     public UserMedicalHistoryDTO(){}


     // Constructor
     public UserMedicalHistoryDTO(String reportID, String docID, String patientID, String description,
                        String dataString, List<String> symptoms, List<String> medicines, List<String> measures,String image,String pdf) {
          this.reportID = reportID;
          this.docID = docID;
          this.patientID = patientID;
          this.description = description;
          this.dataString = dataString;
          this.symptoms = symptoms;
          this.medicines = medicines;
          this.measures = measures;
          this.image = image;
          this.PDF = pdf;
     }

     // Getters
     public String getReportID() {
          return reportID;
     }

     public String getDocID() {
          return docID;
     }

     public String getPatientID() {
          return patientID;
     }

     public String getDescription() {
          return description;
     }

     public String getDataString() {
          return dataString;
     }

     public List<String> getSymptoms() {
          return symptoms;
     }

     public List<String> getMedicines() {
          return medicines;
     }

     public List<String> getMeasures() {
          return measures;
     }

     // Setters
     public void setReportID(String reportID) {
          this.reportID = reportID;
     }

     public void setDocID(String docID) {
          this.docID = docID;
     }

     public void setPatientID(String patientID) {
          this.patientID = patientID;
     }

     public void setDescription(String description) {
          this.description = description;
     }

     public void setDataString(String dataString) {
          this.dataString = dataString;
     }

     public void setSymptoms(List<String> symptoms) {
          this.symptoms = symptoms;
     }

     public void setMedicines(List<String> medicines) {
          this.medicines = medicines;
     }

     public void setMeasures(List<String> measures) {
          this.measures = measures;
     }

     public String getImage() {
          return image;
     }

     public void setImage(String image) {
          this.image = image;
     }

     public String getPDF() {
          return PDF;
     }

     public void setPDF(String PDF) {
          this.PDF = PDF;
     }

     public boolean isIscheckd() {
          return ischeckd;
     }

     public void setSelected(boolean isSelected) {
     }
}
