package com.example.pccoe_oct_2024_hack.DTO;

import java.util.Date;

public class SheduleAppointmentDTO {
    private String doctorName;
    private String doctorAddress;
    private int doctorAge;
    private double doctorFee;
    private String doctorType;
    private Date appointmentDate;
    private String mode; // Online or Offline

    // Default constructor
    public SheduleAppointmentDTO() {
    }

    // Parameterized constructor
    public SheduleAppointmentDTO(String doctorName, String doctorAddress, int doctorAge, double doctorFee,
                                  String doctorType, Date appointmentDate, String mode) {
        this.doctorName = doctorName;
        this.doctorAddress = doctorAddress;
        this.doctorAge = doctorAge;
        this.doctorFee = doctorFee;
        this.doctorType = doctorType;
        this.appointmentDate = appointmentDate;
        this.mode = mode;
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

    // Getter and Setter for appointmentDate
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    // Getter and Setter for mode (Online/Offline)
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "ScheduleAppointmentDTO{" +
                "doctorName='" + doctorName + '\'' +
                ", doctorAddress='" + doctorAddress + '\'' +
                ", doctorAge=" + doctorAge +
                ", doctorFee=" + doctorFee +
                ", doctorType='" + doctorType + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", mode='" + mode + '\'' +
                '}';
    }
}
