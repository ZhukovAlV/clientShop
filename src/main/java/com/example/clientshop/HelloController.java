package com.example.clientshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class HelloController {

    @FXML
    TableView myTable;
    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productAmount;


    @FXML
    private void initialize() {
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    @FXML
    protected void clickButton() {
        System.out.println("Была нажата кнопка ЗАГРУЗИТЬ");

        ObservableList<Product> rowList = FXCollections.observableArrayList();

        try {
            // URL, где база находится и имя базы
            String DB_URL = "jdbc:mysql://localhost:3306/shop";
            // Имя пользователя
            String USER = "user";
            // Пароль
            String PASS = "12345678";
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
                Product product = new Product(rs.getInt("id"),rs.getString("name"),rs.getInt("amount"));
                rowList.add(product);
            }

            System.out.println(rowList.size());
        } catch (SQLException e) {
            // CATCH SOMETHING
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Добавляем данные в таблицу
        myTable.getItems().setAll(rowList);

        // Вариант добавления через цикл
/*        for (Product product : rowList) {
            myTable.getItems().add(product);
        }*/
    }
}