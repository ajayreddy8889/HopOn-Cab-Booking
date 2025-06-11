package com.example.hopon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.sql.*;

public class driveroptionscontroller {

    @FXML
    private Label acceptdropoff;

    @FXML
    private Label acceptmail;

    @FXML
    private Label acceptname;

    @FXML
    private Label acceptphone;

    @FXML
    private Label acceptpickup;

    @FXML
    private AnchorPane acceptridepane;

    @FXML
    private Label accepttime;

    @FXML
    private Button bookridebutton;

    @FXML
    private Button cancelbutton;

    @FXML
    private Pane cancelpopup;

    @FXML
    private Button contactus;

    @FXML
    private Label currdrop;

    @FXML
    private Button currentridebutton;

    @FXML
    private AnchorPane currentridepane;

    @FXML
    private Label currname;

    @FXML
    private Label currnum;

    @FXML
    private Label currpick;

    @FXML
    private Label currtime;

    @FXML
    private Button nobutton;

    @FXML
    private Button ridehistorybutton;

    @FXML
    private AnchorPane ridehistorypane;

    @FXML
    private Button signinforusertopbutton;

    @FXML
    private Button yesbutton;

    @FXML
    private TableView<DriverRideHistory> driveTable;

    @FXML
    private TableColumn<DriverRideHistory, Integer> rideIdCol;

    @FXML
    private TableColumn<DriverRideHistory, String> pickupCol;

    @FXML
    private TableColumn<DriverRideHistory, String> dropCol;

    @FXML
    private TableColumn<DriverRideHistory, Integer> passengerCol;

    @FXML
    private TableColumn<DriverRideHistory, String> riderNameCol;

    ObservableList<DriverRideHistory> driveData = FXCollections.observableArrayList();


    public void initialize() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT RideID FROM ridehistory WHERE DriverID IS NULL");
            int rideId = 0;
            if (rs1.next()) {
                rideId = rs1.getInt("RideID");
            }
            rs1.close();
            stmt1.close();

            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM currentride WHERE RideID = " + rideId);
            int riderId = 0;
            if (rs2.next()) {
                acceptdropoff.setText(rs2.getString("DropoffLocation"));
                acceptpickup.setText(rs2.getString("PickupLocation"));
                accepttime.setText(rs2.getString("Time"));
                riderId = rs2.getInt("RiderID");
            }
            rs2.close();
            stmt2.close();

            Statement stmt3 = connection.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM riderinfo WHERE RiderID = " + riderId);
            if (rs3.next()) {
                acceptname.setText(rs3.getString("RiderName"));
                acceptmail.setText(rs3.getString("RiderEmail"));
                acceptphone.setText(rs3.getString("RiderNumber"));
            }
            rs3.close();
            stmt3.close();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void aboutusclick() {
        Main main = new Main();
        Main.AboutUs();
    }

    @FXML
    void acceptridebuttonclick(ActionEvent event) {
        currentridepane.setVisible(false);
        acceptridepane.setVisible(true);
        ridehistorypane.setVisible(false);
    }

    @FXML
    void contactusclick(ActionEvent event) {
        Main main = new Main();
        Main.ContactUs();
    }

    @FXML
    void currentridebuttonclick(ActionEvent event) {
        currentridepane.setVisible(true);
        acceptridepane.setVisible(false);
        ridehistorypane.setVisible(false);

        loadcurrentdata();
    }

    private void loadcurrentdata() {
        try {
            System.out.println("Current Data");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT DriverID FROM loggeddriver");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("DriverID");
            }
            System.out.println(id);
            rs1.close();
            stmt1.close();

            int rider_id = -1;
            Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM currentride WHERE DriverID =" + id + " AND status=0");
            while (rs2.next()) {
                rider_id = rs2.getInt("RiderID");

                currpick.setText(rs2.getString("PickupLocation"));
                currdrop.setText(rs2.getString("DropoffLocation"));
                currtime.setText(rs2.getString("Time"));
            }
            System.out.println(rider_id);
            rs2.close();
            stmt2.close();

            Statement stmt3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM riderinfo WHERE RiderID =" + rider_id);
            while (rs3.next()) {
                currname.setText(rs3.getString("RiderName"));
                currnum.setText(rs3.getString("RiderNumber"));
            }
            rs3.close();
            stmt3.close();
            System.out.println("Displayed");

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void finishbuttonclick(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT DriverID FROM loggeddriver");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("DriverID");
            }
            rs1.close();
            stmt1.close();

            Statement stmt3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt3.executeUpdate("UPDATE `car_booking`.`currentride` SET `status` = 1 WHERE `DriverID` = "+ id);

            currpick.setText("Pick Up Location");
            currdrop.setText("Drop Off Location");
            currtime.setText("Time");
            currname.setText("Driver Name");
            currnum.setText("Driver Number");

            stmt3.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutclick(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT DriverID FROM loggeddriver");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("DriverID");
            }
            stmt1.executeUpdate("Delete from loggeddriver where DriverID="+ id);
            rs1.close();
            stmt1.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main main = new Main();
        Main.homepage();
    }

    @FXML
    void noclicked(ActionEvent event) {

    }

    @FXML
    void onacceptclick(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT DriverID FROM loggeddriver");
            int did = 0;
            if (rs1.last()) {
                did = rs1.getInt("DriverID");
            }

            ResultSet rs = stmt1.executeQuery("SELECT RideID FROM ridehistory where DriverID is NULL");
            int rid = 0;
            if (rs.next()) {
                rid = rs.getInt("RideID");
            }

            stmt1.executeUpdate("update ridehistory set DriverID="+did+" where RideID ="+rid);
            stmt1.executeUpdate("update currentride set DriverID="+did+" where RideID ="+rid);

            System.out.println("Accepted");

            rs1.close();
            rs.close();
            stmt1.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ridehistorybuttonclick(ActionEvent event) {
        acceptridepane.setVisible(false);
        currentridepane.setVisible(false);
        ridehistorypane.setVisible(true);

        displayhistory();
    }
    public void displayhistory(){
        rideIdCol.setCellValueFactory(new PropertyValueFactory<>("rideID"));
        pickupCol.setCellValueFactory(new PropertyValueFactory<>("pickup"));
        dropCol.setCellValueFactory(new PropertyValueFactory<>("dropoff"));
        passengerCol.setCellValueFactory(new PropertyValueFactory<>("numPassengers"));
        riderNameCol.setCellValueFactory(new PropertyValueFactory<>("riderName"));

        loadRideData();
    }
    private void loadRideData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT DriverID FROM loggeddriver");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("DriverID");
            }

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT rr.RideID, ri.RiderName, rr.PickupLocation, rr.DropoffLocation, rr.NumberofPassengers FROM currentride rr JOIN riderinfo ri ON rr.RiderID = ri.RiderID "+
                    "where DriverID ="+id);

            while (rs.next()) {
                int rideID = rs.getInt("RideID");
                String name = rs.getString("RiderName");
                String pickup = rs.getString("PickupLocation");
                String dropoff = rs.getString("DropoffLocation");
                int numpassengers = rs.getInt("NumberofPassengers");

                driveData.add(new DriverRideHistory(rideID,name,pickup,dropoff,numpassengers));
            }
            driveTable.setItems(driveData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void yesclicked(ActionEvent event) {

    }

}
