package com.example.hopon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.*;

public class signinridercontroller {

    @FXML
    private TextField mail;

    @FXML
    private Label cred;

    @FXML
    private PasswordField pass;

    @FXML
    private Button signinbuttonrider;

    @FXML
    private Button signinfordrivertopbutton;

    @FXML
    private Button signinforusertopbutton;

    @FXML
    private Pane signinpane;

    @FXML
    private Button signupbuttonrider;

    @FXML
    void signinbuttonclick() {
        try {
            if(mail.getText().equals("admin")){
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");
                Statement stmt = connection.createStatement();
                String qry = "select * from admininfo where AdminName = 'admin'";
                ResultSet rs = stmt.executeQuery(qry);

                while (rs.next()) {
                    if (rs.getString("AdminPass").equals(pass.getText())) {
                        System.out.println("Correct");
                        Main main = new Main();
                        Main.admin();
                    } else {
                        cred.setVisible(true);
                    }
                }
            }
            else {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking", "root", "tiger");
                Statement stmt = connection.createStatement();
                String qry = "select * from riderinfo where RiderEmail='" + mail.getText() + "'";
                ResultSet rs = stmt.executeQuery(qry);

                while (rs.next()) {
                    int id = rs.getInt("RiderID");
                    if (rs.getString("RiderPass").equals(pass.getText())) {
                        System.out.println("Correct");
                        Statement stmt1 = connection.createStatement();
                        qry = "INSERT INTO `car_booking`.`loggedrider` (`RiderID`) VALUES ("+ id +");";
                        stmt1.executeUpdate(qry);
                        Main main = new Main();
                        Main.rider();
                    } else {
                        cred.setVisible(true);
                    }
                }
                rs.close();
                stmt.close();
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void contactusclick() {
        Main main = new Main();
        Main.ContactUs();

    }
    @FXML
    void signinuserclick() {
        Main main = new Main();
        Main.SigninforRider();
    }
    @FXML
    void signindriverclick(){
        Main main = new Main();
        Main.SigninforDriver();

    }
    @FXML
    void aboutusclick() {
        Main main = new Main();
        Main.AboutUs();
    }
    @FXML
    void btt()
    {
        Main main = new Main();
        Main.Signuprider();
    }

}
