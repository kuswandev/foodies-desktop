package com.me.foodies.views.screens;

import com.me.foodies.controllers.UserController;
import com.me.foodies.utils.Navigator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RegisterScreen extends VBox {
    UserController userController = new UserController();

    public RegisterScreen() {
        render();
    }

    private void render() {
        Text pageTitle = new Text("Create Account");
        pageTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");

        // Form
        Text nameLabel = new Text("Name");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-padding: 12px 16px");
        VBox nameSection = new VBox(8, nameLabel, nameField);

        Text emailLabel = new Text("Email");
        TextField emailField = new TextField();
        emailField.setStyle("-fx-padding: 12px 16px");
        VBox emailSection = new VBox(8, emailLabel, emailField);

        Text passwordLabel = new Text("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-padding: 12px 16px");
        VBox passwordSection = new VBox(8, passwordLabel, passwordField);

        // Register Button
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9; -fx-padding: 12px 24px");
        registerButton.setOnAction(actionEvent -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            userController.registerUser(email, name, password);
        });

        // Alternative
        Text alternativeText = new Text("Already have account?");
        Text goLogin = new Text("Login");
        goLogin.setStyle("-fx-fill: #0ea5e9; -fx-font-weight: bold");
        goLogin.setOnMouseClicked(mouseEvent -> {
            Navigator.replace(new LoginScreen());
        });
        HBox alternative = new HBox(4, alternativeText, goLogin);
        alternative.setAlignment(Pos.CENTER);

        VBox formSection = new VBox(24, nameSection, emailSection, passwordSection, registerButton, alternative);
        formSection.setAlignment(Pos.CENTER);

        // Main
        VBox main = new VBox(48, pageTitle, formSection);
        main.setAlignment(Pos.CENTER);
        main.setStyle("-fx-max-width: 480px; -fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 64px");

        this.getChildren().addAll(main);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #e2e8f0");
    }
}
