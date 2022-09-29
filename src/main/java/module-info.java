module com.example.clientshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.clientshop to javafx.fxml;
    exports com.example.clientshop;
}