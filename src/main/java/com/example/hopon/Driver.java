package com.example.hopon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Driver {
    private final SimpleIntegerProperty driverID;
    private final SimpleStringProperty driverName;
    private final SimpleStringProperty driverGender;
    private final SimpleStringProperty driverEmail;
    private final SimpleStringProperty driverNumber;
    private final SimpleStringProperty driverDob;

    public Driver(int driverID, String driverName, String driverGender, String driverEmail, String driverNumber, String driverDob) {
        this.driverID = new SimpleIntegerProperty(driverID);
        this.driverName = new SimpleStringProperty(driverName);
        this.driverGender = new SimpleStringProperty(driverGender);
        this.driverEmail = new SimpleStringProperty(driverEmail);
        this.driverNumber = new SimpleStringProperty(driverNumber);
        this.driverDob = new SimpleStringProperty(driverDob);
    }

    public int getDriverID() {return driverID.get();}
    public String getDriverName() {return driverName.get();}
    public String getDriverGender() {return driverGender.get();}
    public String getDriverEmail() {return driverEmail.get();}
    public String getDriverNumber() {return driverNumber.get();}
    public String getDriverDob() {return driverDob.get();}
}