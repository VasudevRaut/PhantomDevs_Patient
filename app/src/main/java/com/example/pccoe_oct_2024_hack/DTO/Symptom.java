package com.example.pccoe_oct_2024_hack.DTO;

import java.util.List;

public class Symptom {
    private String disease;
    private List<String> symptoms;

    // Constructor
    public Symptom(String disease, List<String> symptoms) {
        this.disease = disease;
        this.symptoms = symptoms;
    }

    // Getter for disease
    public String getDisease() {
        return disease;
    }

    // Setter for disease
    public void setDisease(String disease) {
        this.disease = disease;
    }

    // Getter for symptoms
    public List<String> getSymptoms() {
        return symptoms;
    }

    // Setter for symptoms
    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public String toString() {
        return disease + ": " + symptoms.toString();
    }
}
