package com.example.hopon;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label alreadysigninlabel;

    @FXML
    private Button contactus;

    @FXML
    private Button signinfordrivertopbutton;

    @FXML
    private Button signinforusertopbutton;

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
