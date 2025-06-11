package com.example.hopon;

import com.example.hopon.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

public class signupdrivercontroller {

    @FXML
    private Button alreadysigninbutton;

    @FXML
    private Label alreadysigninlabel;

    @FXML
    private TextField city;

    @FXML
    private Button contactus;

    @FXML
    private Label cred;

    @FXML
    private TextField driverdob;

    @FXML
    private TextField drivergender;

    @FXML
    private TextField first_name;

    @FXML
    private TextField last_name;

    @FXML
    private TextField lno;

    @FXML
    private TextField mail;

    @FXML
    private TextField number;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private TextField riderage;

    @FXML
    private Button signinfordrivertopbutton;

    @FXML
    private Button signinforusertopbutton;

    @FXML
    private Button signupbuttondriver;

    @FXML
    private TextField vno;

    @FXML
    void signupdriverclick() {
        String n1=first_name.getText();
        String n2=last_name.getText();

        String name=n1+n2;
        String mail1=mail.getText();
        String number1=number.getText();
        String pass11=pass1.getText();
        String pass12=pass2.getText();
        String vno1=vno.getText();
        String lno1=lno.getText();
        String cit=city.getText();
        String dob = driverdob.getText();
        String gender = drivergender.getText();
        int age = (Integer) parseInt(riderage.getText());

        if(pass11.equals(pass12))
        {
            try
            {
                Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/car_booking","root","tiger");
                Statement stmt= connection.createStatement();
                String qry="insert into driverinfo (`DriverName`,`DriverEmail`,`DriverNumber`,`DriverPass`,`LiscenceNumber`,`VehicleType`,`Gender`,`DOB`,`Age`) values ('"+name+"','"+mail1+"','"+number1+"','"+pass11+"','"+lno1+"','"+vno1+"','"+gender+"','"+dob+"',"+age+")";
                stmt.executeUpdate(qry);
                System.out.println("Created");
                Main main=new Main();
                Main.SigninforDriver();
            }
            catch (SQLException e)
            {
                if (e.getMessage().contains("Email already exists")) {
                    cred.setVisible(true);
                } else {
                    e.printStackTrace();
                }
            }
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

}
