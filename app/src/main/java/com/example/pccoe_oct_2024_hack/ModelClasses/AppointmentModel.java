package com.example.pccoe_oct_2024_hack.ModelClasses;

import java.io.Serializable;

public class AppointmentModel implements Serializable {
    private String appointmentID;
    private String patientEmail;
    private String doctorEmail;
    private String appointmentPatientName;
    private String appointmentDate;
    private String appointmentStartTime;
    private String appointmentEndTime;
    private String appointmentStatus;

    //added
    private String appointmentLink;

    public AppointmentModel() {
    }

    public AppointmentModel(String appointmentID, String patientEmail, String doctorEmail, String appointmentPatientName, String appointmentDate, String appointmentStartTime, String appointmentEndTime, String appointmentStatus) {
        this.appointmentID = appointmentID;
        this.patientEmail = patientEmail;
        this.doctorEmail = doctorEmail;
        this.appointmentPatientName = appointmentPatientName;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getAppointmentPatientName() {
        return appointmentPatientName;
    }

    public void setAppointmentPatientName(String appointmentPatientName) {
        this.appointmentPatientName = appointmentPatientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public void setAppointmentStartTime(String appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public String getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setAppointmentEndTime(String appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}