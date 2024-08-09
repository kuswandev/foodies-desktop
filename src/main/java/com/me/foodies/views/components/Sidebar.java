package com.me.foodies.views.components;

import com.me.foodies.utils.Navigator;
import com.me.foodies.views.screens.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class Sidebar extends VBox {
    public Sidebar() {
        ImageView homeIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons/home.png"))));
        homeIcon.setFitWidth(18);
        homeIcon.setPreserveRatio(true);
        Text homeLabel = new Text("Home");
        homeLabel.setStyle("-fx-font-size: 16px");
        HBox homeMenu = new HBox(12, homeIcon, homeLabel);
        homeMenu.setOnMouseClicked(mouseEvent -> {
            Navigator.navigate(new HomeScreen());
        });

        ImageView searchIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons/search.png"))));
        searchIcon.setFitWidth(18);
        searchIcon.setPreserveRatio(true);
        Text searchLabel = new Text("Search");
        searchLabel.setStyle("-fx-font-size: 16px");
        HBox searchMenu = new HBox(12, searchIcon, searchLabel);
        searchMenu.setOnMouseClicked(mouseEvent -> {
            Navigator.navigate(new SearchMenuScreen());
        });

        ImageView orderIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons/order.png"))));
        orderIcon.setFitWidth(18);
        orderIcon.setPreserveRatio(true);
        Text orderLabel = new Text("Order");
        orderLabel.setStyle("-fx-font-size: 16px");
        HBox orderMenu = new HBox(12, orderIcon, orderLabel);
        orderMenu.setOnMouseClicked(mouseEvent -> {
            Navigator.navigate(new OrderHistoryScreen());
        });

        ImageView profileIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons/user.png"))));
        profileIcon.setFitWidth(18);
        profileIcon.setPreserveRatio(true);
        Text profileLabel = new Text("Profile");
        profileLabel.setStyle("-fx-font-size: 16px");
        HBox profileMenu = new HBox(12, profileIcon, profileLabel);
        profileMenu.setOnMouseClicked(mouseEvent -> {
            Navigator.navigate(new ProfileScreen());
        });

        ImageView logoutIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons/logout.png"))));
        logoutIcon.setFitWidth(18);
        logoutIcon.setPreserveRatio(true);
        Text logoutLabel = new Text("Logout");
        logoutLabel.setStyle("-fx-font-size: 16px");
        HBox logoutMenu = new HBox(12, logoutIcon, logoutLabel);
        logoutMenu.setOnMouseClicked(mouseEvent -> {
            Navigator.replace(new LoginScreen());
        });

        this.getChildren().addAll(homeMenu, searchMenu, orderMenu, profileMenu, logoutMenu);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 24px");
    }
}
