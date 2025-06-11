module com.example.hopon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hopon to javafx.fxml;
    exports com.example.hopon;
}