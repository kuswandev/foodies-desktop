package com.me.foodies.views.screens;

import com.me.foodies.controllers.MenuController;
import com.me.foodies.models.Menu;
import com.me.foodies.models.User;
import com.me.foodies.utils.Session;
import com.me.foodies.views.components.MenuList;
import com.me.foodies.views.components.Sidebar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HomeScreen extends HBox {
    MenuController menuController = new MenuController();
    Session session = Session.getInstance();

    public HomeScreen() {
        render();
    }

    private void render() {
        User user = session.getUser();
        ArrayList<Menu> latestMenus = menuController.getLatestMenus();
        ArrayList<Menu> popularMenus = menuController.getPopularMenus();

        // Greeting
        Text greeting = new Text("Hello, " + user.name);
        greeting.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Text suggestion = new Text("What do you want to order today?");
        suggestion.setStyle("-fx-font-size: 16px");
        VBox greetingSection = new VBox(16, greeting, suggestion);

        // Popular Menus
        Text popularMenusTitle = new Text("Popular Menus");
        popularMenusTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        ScrollPane popularMenusList = new ScrollPane(new MenuList(popularMenus));
        popularMenusList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox popularMenusSection = new VBox(8, popularMenusTitle, popularMenusList);

        // Latest Menu
        Text latestMenusTitle = new Text("Latest Menus");
        latestMenusTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        ScrollPane latestMenusList = new ScrollPane(new MenuList(latestMenus));
        latestMenusList.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox latestMenusSection = new VBox(8, latestMenusTitle, latestMenusList);

        // Main
        VBox main = new VBox(48, greetingSection, popularMenusSection, latestMenusSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
