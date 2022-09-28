package com.example.clientshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.sql.*;

public class HelloController {

    static String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL = "jdbc:h2:file:C:/WAKILI/WAKILIdb";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static Connection conn = null;

    @FXML
    private TableView myTable;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Была нажата кнопка ЗАГРУЗИТЬ");
/*
        Statement st = null;
        ResultSet rs;
        String driver = "org.h2.Driver";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            st = conn.createStatement();
            String recordQuery = ("SELECT id, KIWI FROM KIWI");

            rs = st.executeQuery(recordQuery);
            while (rs.next()) {
                ObservableList row = FXCollections.observableArrayList();

                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                    System.out.println(row);
                }

                data.add(row);

            }
            lovelyStudents.setItems(data);

        } catch (ClassNotFoundException | SQLException ex) {
            // CATCH SOMETHING
        }*/
    }

}