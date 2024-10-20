package com.example.pccoe_oct_2024_hack.DTO;

public class DoctorDTO {
    private String doctorName;
    private String doctorAddress;
    private int doctorAge;
    private double doctorFee;
    private String doctorType;

    // Default constructor
    public DoctorDTO() {
    }

    // Parameterized constructor
    public DoctorDTO(String doctorName, String doctorAddress, int doctorAge, double doctorFee, String doctorType) {
        this.doctorName = doctorName;
        this.doctorAddress = doctorAddress;
        this.doctorAge = doctorAge;
        this.doctorFee = doctorFee;
        this.doctorType = doctorType;
    }

    // Getter and Setter for doctorName
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    // Getter and Setter for doctorAddress
    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    // Getter and Setter for doctorAge
    public int getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(int doctorAge) {
        this.doctorAge = doctorAge;
    }

    // Getter and Setter for doctorFee
    public double getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(double doctorFee) {
        this.doctorFee = doctorFee;
    }

    // Getter and Setter for doctorType
    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "doctorName='" + doctorName + '\'' +
                ", doctorAddress='" + doctorAddress + '\'' +
                ", doctorAge=" + doctorAge +
                ", doctorFee=" + doctorFee +
                ", doctorType='" + doctorType + '\'' +
                '}';
    }
}
