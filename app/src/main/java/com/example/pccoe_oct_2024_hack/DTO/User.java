package com.example.pccoe_oct_2024_hack.DTO;



public class User {
    private String userEmail;
    private String userName;
    private int userAge;
    double lati,longi;
    private String userGender;
    private String userBloodGroup;

    public User() {
    }

    // Constructor
    public User( String userEmail,
                String userName, int userAge, String userGender,
                String userBloodGroup,double lati,double longi) {

        this.userEmail = userEmail;

        this.userName = userName;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userBloodGroup = userBloodGroup;
        this.lati = lati;
        this.longi = longi;
    }

    // Getters


    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public String getUserEmail() {
        return userEmail;
    }


    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserBloodGroup() {
        return userBloodGroup;
    }

    // Setters

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        this.userBloodGroup = userBloodGroup;
    }
}
