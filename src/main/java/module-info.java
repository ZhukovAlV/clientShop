module com.example.clientshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clientshop to javafx.fxml;
    exports com.example.clientshop;
}