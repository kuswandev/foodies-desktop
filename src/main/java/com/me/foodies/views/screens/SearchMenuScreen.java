package com.me.foodies.views.screens;

import com.me.foodies.controllers.MenuController;
import com.me.foodies.models.Menu;
import com.me.foodies.views.components.Appbar;
import com.me.foodies.views.components.MenuList;
import com.me.foodies.views.components.Sidebar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchMenuScreen extends HBox {
    MenuController menuController = new MenuController();

    public SearchMenuScreen() {
        render();
    }

    private void render() {
        Appbar appbar = new Appbar("Search Menu");

        // Search Result
        VBox searchResultSection = new VBox();

        // Search Form
        TextField searchField = new TextField();
        searchField.setStyle("-fx-padding: 12px 16px");
        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9; -fx-padding: 12px 24px");
        searchButton.setOnAction(actionEvent -> {
            String name = searchField.getText();
            ArrayList<Menu> menus = menuController.searchMenus(name);
            MenuList menuList = new MenuList(menus);
            ScrollPane menuListScroll = new ScrollPane(menuList);
            menuListScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            searchResultSection.getChildren().clear();
            searchResultSection.getChildren().add(menuListScroll);
        });

        HBox formSection = new HBox(24, searchField, searchButton);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        // Main
        VBox main = new VBox(48, appbar, formSection, searchResultSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
