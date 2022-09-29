package com.example.clientshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class HelloController {

    @FXML
    private TableView myTable;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("Была нажата кнопка ЗАГРУЗИТЬ");

        // URL, где база находится и имя базы
        String DB_URL = "jdbc:mysql://localhost:3306/test";
        // Имя пользователя
        String USER = "root";
        // Пароль
        String PASS = "20122012";

        try {
            // Драйвер для подключения к БД
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Наше подключение
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Наш запрос
            String query = "SELECT * FROM product;";
            // Создаем заявку для нашего запроса (сеанс)
            Statement st = conn.createStatement();
            // Выполняем запрос и записываем результат в ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("amount"));
            }

        } catch (SQLException e) {
            // CATCH SOMETHING
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

/*


        try {
            Class.forName(driver);

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