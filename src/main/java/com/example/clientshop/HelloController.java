package com.example.clientshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private Button insertButton;


    @FXML
    private void initialize() {
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));


        insertButton.setDisable(true);
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

        // Делаем видимой кнопку Edit
        insertButton.setDisable(false);
    }


    @FXML
    protected void clickInsertButton() {
        System.out.println("Была нажата кнопка Insert");

        // Parent parent = FXMLLoader.load(getClass().getResource("insert-view.fxml"));
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("insert-view.fxml"));
            Parent parent = loader.load();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Создать новый товар");

            stage.showAndWait();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void clickEditButton() {
        System.out.println("Была нажата кнопка Edit");


    }

    @FXML
    protected void clickDeleteButton() {
        System.out.println("Была нажата кнопка Delete");


    }
}