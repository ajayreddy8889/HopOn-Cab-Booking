package com.example.hopon;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RideRequest {
    private final SimpleIntegerProperty rideId;
    private final SimpleStringProperty pickupLocation;
    private final SimpleStringProperty dropLocation;
    private final SimpleStringProperty driverNum;
    private final SimpleStringProperty vehicleType;
    private final SimpleStringProperty driverName;

    public RideRequest(int rideId, String pickupLocation, String dropLocation, String driverNum, String vehicleType, String driverName) {
        this.rideId = new SimpleIntegerProperty(rideId);
        this.pickupLocation = new SimpleStringProperty(pickupLocation);
        this.dropLocation = new SimpleStringProperty(dropLocation);
        this.driverNum = new SimpleStringProperty(driverNum);
        this.vehicleType = new SimpleStringProperty(vehicleType);
        this.driverName = new SimpleStringProperty(driverName);
    }

    public int getRideId() { return rideId.get(); }
    public String getPickupLocation() { return pickupLocation.get(); }
    public String getDropLocation() { return dropLocation.get(); }
    public String getDriverNum() { return driverNum.get(); }
    public String getVehicleType() { return vehicleType.get(); }
    public String getDriverName() { return driverName.get(); }
}
