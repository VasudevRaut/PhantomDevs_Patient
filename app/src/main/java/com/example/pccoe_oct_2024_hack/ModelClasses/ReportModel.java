package com.example.pccoe_oct_2024_hack.ModelClasses;

import java.io.Serializable;
import java.util.List;

public class ReportModel implements Serializable{
    private String reportDate;
    private String docEmail; //change to docEmail
    private String docName;
    private String patientEmail; //change to patientEmail

    private String blockID;

//    private String description;
//    private List<String> symptoms;
//    private List<String> medicines;
//    private List<String> measures;
    private String image;
    private String PDF;

    public ReportModel() {
    }

    // Constructor


    public ReportModel(String reportDate, String docEmail, String docName, String patientEmail, String blockID, String image, String PDF) {
        this.reportDate = reportDate;
        this.docEmail = docEmail;
        this.docName = docName;
        this.patientEmail = patientEmail;
        this.blockID = blockID;
        this.image = image;
        this.PDF = PDF;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getDocEmail() {
        return docEmail;
    }

    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
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
}
