package com.example.clientshop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class HelloController {

    @FXML
    private TableView<Product> myTable;

    Product selectedProduct;

    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productAmount;

    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    @FXML
    private Button addButton;

    @FXML
    private void initialize() {
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        deleteButton.setVisible(false);
        editButton.setVisible(false);
        addButton.setVisible(false);
        getSelected();
    }

    @FXML
    protected void onLoadButtonClick() {
        System.out.println("Была нажата кнопка ЗАГРУЗИТЬ");

        ObservableList<Product> rowList = FXCollections.observableArrayList();

        try {
            // URL, где база находится и имя базы
            String DB_URL = "jdbc:h2:file:~/testdb";
            // Имя пользователя
            String USER = "user";
            // Пароль
            String PASS = "12345678";
            // Драйвер для подключения к БД
            Class.forName("org.h2.Driver");
            // Наше подключение
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Наш запрос
            String query = "SELECT * FROM product;";
            // Создаем заявку для нашего запроса (сеанс)
            Statement st = conn.createStatement();
            // Выполняем запрос и записываем результат в ResultSet
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),
                        rs.getString("name"), rs.getInt("amount"));
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

        // Отображаем кнопку добавления
        addButton.setVisible(true);
    }

    @FXML
    protected void onDeleteButtonClick() {
        System.out.println("Была нажата кнопка Удалить");
/*
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
            String query = "DELETE FROM product WHERE id = " + selectedProduct.getId();
            // Создаем заявку для нашего запроса (сеанс)
            Statement st = conn.createStatement();
            // Выполняем запрос и записываем результат в ResultSet
            st.executeUpdate(query);

            // Обновляем таблицу
            onLoadButtonClick();
        } catch (SQLException e) {
            // CATCH SOMETHING
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    @FXML
    protected void onEditButtonClick() throws IOException {
        System.out.println("Была нажата кнопка Редактировать");

/*        FXMLLoader loader = new FXMLLoader(getClass().getResource("update-view.fxml"));
        Parent parent = loader.load();

        // Передаем данные полей продукта в новую сцену
        UpdateController controller = loader.getController();
        controller.preloadData(selectedProduct);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Обновить товар");

        stage.showAndWait();

        onLoadButtonClick();*/
    }

    @FXML
    protected void onInsertButtonClick() throws IOException {
        System.out.println("Была нажата кнопка Добавить");

/*        Parent parent = FXMLLoader.load(getClass().getResource("insert-view.fxml"));

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Создать новый товар");

        stage.showAndWait();

        onLoadButtonClick();*/
    }

    @FXML
    public void getSelected() {
        myTable.setOnMouseClicked(t -> {
            selectedProduct = myTable.getSelectionModel().getSelectedItem();
            editButton.setVisible(true);
            deleteButton.setVisible(true);
        });
    }
}