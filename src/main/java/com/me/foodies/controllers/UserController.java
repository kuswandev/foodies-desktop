package com.me.foodies.controllers;

import com.me.foodies.models.User;
import com.me.foodies.utils.Database;
import com.me.foodies.utils.Navigator;
import com.me.foodies.utils.Session;
import com.me.foodies.views.screens.HomeScreen;
import com.me.foodies.views.screens.LoginScreen;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {

    public void loginUser(String email, String password) {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("email"),
                        result.getString("name")
                );
                Session.getInstance().setUser(user);
                Navigator.replace(new HomeScreen());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email or password is incorrect");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void registerUser(String email, String name, String password) {
        try {
            Connection connection = Database.getConnection();
            String query = "INSERT INTO users (email, name, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.executeUpdate();
            Navigator.replace(new LoginScreen());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Email already registered");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
