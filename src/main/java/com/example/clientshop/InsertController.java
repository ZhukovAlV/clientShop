package com.example.clientshop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField amountField;

    @FXML
    private Button cancelButton;

    @FXML
    protected void onInsertButtonClick() {
        System.out.println("Была нажата кнопка Ок");

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
            String query = "INSERT INTO product(name, amount) VALUES (?, ?)";
            // Создаем заявку для нашего запроса (сеанс)
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            // Выполняем запрос и записываем результат в ResultSet
            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, amountField.getText());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // CATCH SOMETHING
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onCancelButtonClick() {
        System.out.println("Была нажата кнопка Отмена");

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
