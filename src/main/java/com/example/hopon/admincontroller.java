package com.example.hopon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class admincontroller implements Initializable {

    @FXML
    private Button contactus;

    @FXML
    private ComboBox<String> genderFilter;

    @FXML
    private TableView<Driver> driverTable;

    @FXML
    private TableColumn<Driver, Integer> driverID;

    @FXML
    private TableColumn<Driver, String> driverName;

    @FXML
    private TableColumn<Driver, String> driverGender;

    @FXML
    private TableColumn<Driver, String> driverEmail;

    @FXML
    private TableColumn<Driver, String> driverNumber;

    @FXML
    private TableColumn<Driver, String> driverDOB;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> userID;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, String> userGender;

    @FXML
    private TableColumn<User, String> userEmail;

    @FXML
    private TableColumn<User, String> userNumber;

    @FXML
    private TableColumn<User, String> userDOB;

    @FXML
    private Button logoutbutton;

    @FXML
    private AnchorPane profileanchor;

    public void initialize(URL location, ResourceBundle resources) {
        setupDriverTable();
        setupRiderTable();
    }

    private void setupDriverTable() {
        driverID.setCellValueFactory(new PropertyValueFactory<>("driverID"));
        driverName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        driverEmail.setCellValueFactory(new PropertyValueFactory<>("driverEmail"));
        driverNumber.setCellValueFactory(new PropertyValueFactory<>("driverNumber"));
        driverGender.setCellValueFactory(new PropertyValueFactory<>("driverGender"));
        driverDOB.setCellValueFactory(new PropertyValueFactory<>("driverDob"));
    }

    private void setupRiderTable() {
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        userNumber.setCellValueFactory(new PropertyValueFactory<>("userNumber"));
        userGender.setCellValueFactory(new PropertyValueFactory<>("userGender"));
        userDOB.setCellValueFactory(new PropertyValueFactory<>("userDob"));
    }


    @FXML
    void contactusclick(ActionEvent event) {
        Main main = new Main();
        Main.ContactUs();
    }

    @FXML
    void aboutusclick(ActionEvent event){
        Main main = new Main();
        Main.AboutUs();
    }

    @FXML
    void filterByGender(ActionEvent event) {
        String selectedGender = genderFilter.getValue();
        loadDriverData(selectedGender);
        loadUserData(selectedGender);
    }

    private void loadDriverData(String gender) {
        ObservableList<Driver> driverList = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger")) {
            String query = "SELECT * FROM driverinfo";
            if (!gender.equals("All")) {
                query += " WHERE Gender = ?";
            }
            PreparedStatement stmt = conn.prepareStatement(query);
            if (!gender.equals("All")) {
                stmt.setString(1, gender);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                driverList.add(new Driver(
                        rs.getInt("DriverID"),
                        rs.getString("DriverName"),
                        rs.getString("Gender"),
                        rs.getString("DriverEmail"),
                        rs.getString("DriverNumber"),
                        rs.getString("DOB")
                ));
            }
            driverTable.setItems(driverList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadUserData(String gender) {
        ObservableList<User> userList = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger")) {
            String query = "SELECT * FROM riderinfo";
            if (!gender.equals("All")) {
                query += " WHERE Gender = ?";
            }
            PreparedStatement stmt = conn.prepareStatement(query);
            if (!gender.equals("All")) {
                stmt.setString(1, gender);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("RiderID"),
                        rs.getString("RiderName"),
                        rs.getString("Gender"),
                        rs.getString("RiderEmail"),
                        rs.getString("RiderNumber"),
                        rs.getString("DOB")
                ));
            }
            userTable.setItems(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutclick(ActionEvent event) {
        Main main = new Main();
        Main.homepage();
    }

}
