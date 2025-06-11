package com.example.hopon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.*;

public class rideroptionscontroller {

    @FXML
    private AnchorPane Currentridepane;

    @FXML
    private Button bookridebutton;

    @FXML
    private AnchorPane bookridepane;

    @FXML
    private Pane cancelpopup;

    @FXML
    private Button contactus;

    @FXML
    private Button currentridebutton;

    @FXML
    private Label drivname;

    @FXML
    private Label drivnum;

    @FXML
    private Label drop;

    @FXML
    private Label na;

    @FXML
    private Label nu;

    @FXML
    private Label em;

    @FXML
    private TextField dropoff;

    @FXML
    private Button finishbutton;

    @FXML
    private Button nobutton;

    @FXML
    private TextField passengers;

    @FXML
    private Label pick;

    @FXML
    private TextField pickup;

    @FXML
    private Button ridehistorybutton;

    @FXML
    private AnchorPane ridehistorypane;

    @FXML
    private Button signinfordrivertopbutton;

    @FXML
    private Label tim;

    @FXML
    private TextField time;

    @FXML
    private Button yesbutton;

    @FXML
    private TableView<RideRequest> rideTable;

    @FXML
    private TableColumn<RideRequest, Integer> rideIdCol;

    @FXML
    private TableColumn<RideRequest, String> pickupCol;

    @FXML
    private TableColumn<RideRequest, String> dropCol;

    @FXML
    private TableColumn<RideRequest, String> driverNumCol;

    @FXML
    private TableColumn<RideRequest, String> vehicleTypeCol;

    @FXML
    private TableColumn<RideRequest, String> driverNameCol;

    ObservableList<RideRequest> rideData = FXCollections.observableArrayList();

    @FXML
    void bookridebuttonclick(ActionEvent event) {
        String pick=pickup.getText();
        String pass=passengers.getText();
        String drop=dropoff.getText();
        String time1=time.getText();

        try
        {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking","root","tiger");
            Statement stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String qry = "select RiderID from loggedrider";
            ResultSet rs = stmt.executeQuery(qry);
            int id = 0;
            if(rs.last()){
                id = rs.getInt("RiderID");
            }
            qry="insert into currentride (`PickupLocation`,`DropoffLocation`,`NumberofPassengers`,`Time`,`RiderID`, `status`) values ('"+pick+"','"+drop+"','"+pass+"','"+time1+"',"+ id +",0)";
            stmt.executeUpdate(qry);
            System.out.println("Created");
            qry = "select RideID from currentride";
            rs = stmt.executeQuery(qry);
            int rid = 0;
            if(rs.last()){
                rid = rs.getInt("RideID");
            }
            qry="insert into ridehistory (`RideID`,`RiderID`) values ("+rid+","+id+")";
            stmt.executeUpdate(qry);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void bookridepageclick(ActionEvent event) {
        Currentridepane.setVisible(false);
        bookridepane.setVisible(true);
        ridehistorypane.setVisible(false);
    }

    @FXML
    void contactusclick(ActionEvent event) {
        Main main = new Main();
        Main.ContactUs();
    }

    @FXML
    void currentridepageclick(ActionEvent event) {
        Currentridepane.setVisible(true);
        bookridepane.setVisible(false);
        ridehistorypane.setVisible(false);

        loadcurrentdata();
    }

    private void loadcurrentdata() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT RiderID FROM loggedrider");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("RiderID");
            }
            rs1.close();
            stmt1.close();

            Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM riderinfo WHERE RiderID=" + id);
            while (rs2.next()) {
                na.setText(rs2.getString("RiderName"));
                nu.setText(rs2.getString("RiderNumber"));
                em.setText(rs2.getString("RiderEmail"));
            }
            rs2.close();
            stmt2.close();

            Statement stmt3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM currentride WHERE RiderID =" + id + " AND status=0");
            while (rs3.next()) {
                int driv_id = rs3.getInt("DriverID");
                System.out.println(driv_id);
                if (rs3.wasNull()) {
                    drivname.setText("Driver Not Assigned");
                    drivnum.setText("Driver Not Assigned");
                } else {
                    Statement stmt4 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs4 = stmt4.executeQuery("SELECT DriverName, DriverNumber FROM driverinfo WHERE DriverID = " + driv_id);
                    while (rs4.next()) {
                        drivname.setText(rs4.getString("DriverName"));
                        drivnum.setText(rs4.getString("DriverNumber"));
                    }
                    rs4.close();
                    stmt4.close();
                }

                pick.setText(rs3.getString("PickupLocation"));
                drop.setText(rs3.getString("DropoffLocation"));
                tim.setText(rs3.getString("Time"));
            }
            rs3.close();
            stmt3.close();

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
            ResultSet rs1 = stmt1.executeQuery("SELECT RiderID FROM loggedrider");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("RiderID");
            }
            rs1.close();
            stmt1.close();

            Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM riderinfo WHERE RiderID=" + id);
            while (rs2.next()) {
                na.setText(rs2.getString("RiderName"));
                nu.setText(rs2.getString("RiderNumber"));
                em.setText(rs2.getString("RiderEmail"));
            }
            rs2.close();
            stmt2.close();

            Statement stmt3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt3.executeUpdate("UPDATE `car_booking`.`currentride` SET `status` = 1 WHERE `RiderID` = "+ id);

            pick.setText("Pick Up Location");
            drop.setText("Drop Off Location");
            tim.setText("Time");
            drivname.setText("Driver Name");
            drivnum.setText("Driver Number");

            stmt3.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutbuttonclick(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT RiderID FROM loggedrider");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("RiderID");
            }
            stmt1.executeUpdate("Delete from loggedrider where RiderID="+ id);
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
    void ridehistorypageclick(ActionEvent event) {
        Currentridepane.setVisible(false);
        bookridepane.setVisible(false);
        ridehistorypane.setVisible(true);

        displayhistory();
    }

    public void displayhistory(){
        rideIdCol.setCellValueFactory(new PropertyValueFactory<>("rideId"));
        pickupCol.setCellValueFactory(new PropertyValueFactory<>("pickupLocation"));
        dropCol.setCellValueFactory(new PropertyValueFactory<>("dropLocation"));
        driverNumCol.setCellValueFactory(new PropertyValueFactory<>("driverNum"));
        vehicleTypeCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        driverNameCol.setCellValueFactory(new PropertyValueFactory<>("driverName"));

        loadRideData();
    }

    private void loadRideData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");

            Statement stmt1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery("SELECT RiderID FROM loggedrider");
            int id = 0;
            if (rs1.last()) {
                id = rs1.getInt("RiderID");
            }

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT rr.RideID, rr.PickupLocation, rr.DropoffLocation, di.DriverNumber, di.VehicleType, di.DriverName FROM currentride rr JOIN driverinfo di ON rr.DriverID = di.DriverID where RiderID ="+id);

            while (rs.next()) {
                int rideId = rs.getInt("RideID");
                String pickup = rs.getString("PickupLocation");
                String drop = rs.getString("DropoffLocation");
                String vno = rs.getString("DriverNumber");
                String vtype = rs.getString("VehicleType");
                String dname = rs.getString("DriverName");

                rideData.add(new RideRequest(rideId, pickup, drop, vno, vtype, dname));
            }

            rideTable.setItems(rideData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void yesclicked(ActionEvent event) {

    }
    @FXML
    void aboutusclick() {
        Main main = new Main();
        Main.AboutUs();
    }

}
