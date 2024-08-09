package com.me.foodies.views.screens;

import com.me.foodies.controllers.MenuController;
import com.me.foodies.models.Menu;
import com.me.foodies.utils.Navigator;
import com.me.foodies.views.components.Appbar;
import com.me.foodies.views.components.Sidebar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Objects;

public class MenuDetailScreen extends HBox {
    MenuController menuController = new MenuController();
    int menuId;

    public MenuDetailScreen(int id) {
        menuId = id;
        render();
    }

    private void render() {
        Menu menu = menuController.getMenuById(menuId);

        Appbar appbar = new Appbar("Menu Detail");

        // Menu Detail
        Rectangle clip = new Rectangle(180, 180);
        clip.setArcHeight(24);
        clip.setArcWidth(24);
        ImageView menuImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menus/" + menu.image))));
        menuImage.setFitWidth(180);
        menuImage.setPreserveRatio(true);
        menuImage.setClip(clip);

        Text menuName = new Text(menu.name);
        menuName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Text menuCategory = new Text(menu.category);
        menuCategory.setStyle("-fx-font-size: 16px");
        Text menuRating = new Text("★" + menu.rating);
        Text menuPrice = new Text("Rp." + menu.price);
        menuPrice.setStyle("-fx-fill: #0ea5e9; -fx-font-size: 18px; -fx-font-weight: bold");
        VBox menuInfo = new VBox(8, menuName, menuCategory, menuRating, menuPrice);

        HBox menuMainInfo = new HBox(24, menuImage, menuInfo);

        Text menuDescriptionText = new Text(menu.description);
        TextFlow menuDescription = new TextFlow(menuDescriptionText);
        menuDescription.setLineSpacing(10);
        menuDescription.setStyle("-fx-font-size: 14px");

        // Order Button
        Button orderButton = new Button("Order Menu");
        orderButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9; -fx-padding: 12px 24px");
        orderButton.setOnAction(actionEvent -> {
            Navigator.navigate(new OrderMenuScreen(menuId));
        });
        HBox orderSection = new HBox(orderButton);
        orderSection.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setVgrow(orderSection, Priority.ALWAYS);

        // Main
        VBox main = new VBox(48, appbar, menuMainInfo, menuDescription, orderSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
