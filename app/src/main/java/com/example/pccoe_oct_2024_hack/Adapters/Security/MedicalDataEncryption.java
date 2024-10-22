package com.example.pccoe_oct_2024_hack.Adapters.Security;


import java.util.ArrayList;
import java.util.List;

public class MedicalDataEncryption {

    String description;
    List<String> symptomsList = new ArrayList<>();
    List<String> medicinesList = new ArrayList<>();
    List<String> measuresList = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSymptomsList() {
        return symptomsList;
    }

    public void setSymptomsList(List<String> symptomsList) {
        this.symptomsList = symptomsList;
    }

    public List<String> getMedicinesList() {
        return medicinesList;
    }

    public void setMedicinesList(List<String> medicinesList) {
        this.medicinesList = medicinesList;
    }

    public List<String> getMeasuresList() {
        return measuresList;
    }

    public void setMeasuresList(List<String> measuresList) {
        this.measuresList = measuresList;
    }

    // Constructor to initialize the fields
    public MedicalDataEncryption(String description, List<String> symptomsList, List<String> medicinesList, List<String> measuresList) {
        this.description = description;
        this.symptomsList = symptomsList;
        this.medicinesList = medicinesList;
        this.measuresList = measuresList;
    }

    public MedicalDataEncryption(){}

    // Convert the data into a single string
    public String convertToString() {
        String symptoms = String.join(",", symptomsList);
        String medicines = String.join(",", medicinesList);
        String measures = String.join(",", measuresList);

        // Combine all fields using a delimiter like "||" between different categories
        return description + "||" + symptoms + "||" + medicines + "||" + measures;
    }

    // Static method to convert the string back to its original state
    public static MedicalDataEncryption fromString(String combinedData) {
        // Split the string by "||" to separate the description, symptoms, medicines, and measures
        String[] parts = combinedData.split("\\|\\|");

        // Convert symptoms, medicines, and measures back into lists by splitting on commas
        String description = parts[0];
        List<String> symptomsList = List.of(parts[1].split(","));
        List<String> medicinesList = List.of(parts[2].split(","));
        List<String> measuresList = List.of(parts[3].split(","));

        return new MedicalDataEncryption(description, symptomsList, medicinesList, measuresList);
    }

}