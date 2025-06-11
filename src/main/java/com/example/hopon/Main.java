package com.example.hopon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        homepage();
    }

    public static void Accept() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("AcceptRide.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Payment");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void book() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("book.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Book");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void payment() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("payment.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Payment");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void profile() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("profile.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Profile");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AboutUs() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("aboutus.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("About Us");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ContactUs() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("contactus.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Contact Us");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Signuprider() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("signuprider.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Sign Up Rider");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void SigninforRider() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("signinrider.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Sign in for Rider");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Signupdriver() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("signupdriver.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Sign Up Driver");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SigninforDriver() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("signindriver.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Sign in as Driver");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void homepage() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("homepage.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Home Page");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void admin() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("admin.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Admin Page");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void rider() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("rideoptions.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Rider Page");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void driver() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("driveroptions.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Driver Page");
            Scene scene = new Scene(root, 1550, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch();
    }
}
