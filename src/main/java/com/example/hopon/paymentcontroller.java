package com.example.hopon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class paymentcontroller {

    @FXML
    private AnchorPane cardpayment;

    @FXML
    private Button cardpaymentbutton;

    @FXML
    private Button cardpaymentconfirmbutton;

    @FXML
    private AnchorPane netbanking;

    @FXML
    private Button netbankingbutton;

    @FXML
    private Button netbankingconfirmbutton;

    @FXML
    private Button upibutton;

    @FXML
    private Button upiconfirmbutton;

    @FXML
    private AnchorPane upipayment;

    @FXML
    void b1() {
        Main main = new Main();
        Main.profile();
    }
    @FXML
    void upi() {
        upipayment.setVisible(true);
        cardpayment.setVisible(false);
        netbanking.setVisible(false);
    }
    @FXML
    void card() {
        upipayment.setVisible(false);
        cardpayment.setVisible(true);
        netbanking.setVisible(false);
    }
    @FXML
    void net() {
        upipayment.setVisible(false);
        cardpayment.setVisible(false);
        netbanking.setVisible(true);
    }

    @FXML
    void b2() {
        Main main = new Main();
        Main.profile();
    }

    @FXML
    void b3() {
        Main main = new Main();
        Main.profile();
    }

}
