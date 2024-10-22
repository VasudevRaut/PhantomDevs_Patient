package com.example.pccoe_oct_2024_hack.DTO;

import java.io.Serializable;

public class PaymentDTO implements Serializable {
    private String paymentID;
    private String paymentPatientName;
    private String paymentStatus;
    private int paymentAmount;

    // Constructor
    public PaymentDTO() {
    }

    public PaymentDTO(String paymentID, String paymentPatientName, String paymentStatus, int paymentAmount) {
        this.paymentID = paymentID;
        this.paymentPatientName = paymentPatientName;
        this.paymentStatus = paymentStatus;
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentPatientName() {
        return paymentPatientName;
    }

    public void setPaymentPatientName(String paymentPatientName) {
        this.paymentPatientName = paymentPatientName;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}