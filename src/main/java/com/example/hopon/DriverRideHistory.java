package com.example.hopon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DriverRideHistory {
    private final SimpleIntegerProperty rideID;
    private final SimpleStringProperty riderName;
    private final SimpleStringProperty pickup;
    private final SimpleStringProperty dropoff;
    private final SimpleIntegerProperty numPassengers;

    public DriverRideHistory(int rideID, String riderName, String pickup, String dropoff, int numPassengers) {
        this.rideID = new SimpleIntegerProperty(rideID);
        this.riderName = new SimpleStringProperty(riderName);
        this.pickup = new SimpleStringProperty(pickup);
        this.dropoff = new SimpleStringProperty(dropoff);
        this.numPassengers = new SimpleIntegerProperty(numPassengers);
    }

    public int getRideID() { return rideID.get(); }
    public String getRiderName() { return riderName.get(); }
    public String getPickup() { return pickup.get(); }
    public String getDropoff() { return dropoff.get(); }
    public int getNumPassengers() { return numPassengers.get(); }
}
