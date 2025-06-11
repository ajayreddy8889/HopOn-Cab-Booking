package com.example.hopon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleIntegerProperty userID;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty userGender;
    private final SimpleStringProperty userEmail;
    private final SimpleStringProperty userNumber;
    private final SimpleStringProperty userDob;

    public User(int userID, String userName, String userGender, String userEmail, String userNumber, String userDob) {
        this.userID = new SimpleIntegerProperty(userID);
        this.userName = new SimpleStringProperty(userName);
        this.userGender = new SimpleStringProperty(userGender);
        this.userEmail = new SimpleStringProperty(userEmail);
        this.userNumber = new SimpleStringProperty(userNumber);
        this.userDob = new SimpleStringProperty(userDob);
    }

    public int getUserID() {return userID.get();}
    public String getUserName() {return userName.get();}
    public String getUserGender() {return userGender.get();}
    public String getUserEmail() {return userEmail.get();}
    public String getUserNumber() {return userNumber.get();}
    public String getUserDob() {return userDob.get();}
}
