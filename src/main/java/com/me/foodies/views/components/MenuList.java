package com.me.foodies.views.components;

import com.me.foodies.models.Menu;
import com.me.foodies.utils.Navigator;
import com.me.foodies.views.screens.MenuDetailScreen;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Objects;

public class MenuList extends HBox {
    public MenuList(ArrayList<Menu> menus) {
        menus.forEach(menu -> {
            Rectangle clip = new Rectangle(120, 120);
            clip.setArcHeight(24);
            clip.setArcWidth(24);
            ImageView menuImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menus/" + menu.image))));
            menuImage.setFitWidth(120);
            menuImage.setPreserveRatio(true);
            menuImage.setClip(clip);

            Text menuName = new Text(menu.name);
            menuName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");
            Text menuPrice = new Text("Rp." + menu.price);
            menuPrice.setStyle("-fx-fill: #0ea5e9; -fx-font-weight: bold");
            Text menuRating = new Text("★" + menu.rating);
            HBox menuInfo = new HBox(18, menuPrice, menuRating);

            VBox menuCard = new VBox(8, menuImage, menuName, menuInfo);
            menuCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 8px");
            menuCard.setOnMouseClicked(mouseEvent -> {
                Navigator.navigate(new MenuDetailScreen(menu.id));
            });

            this.getChildren().add(menuCard);
            this.setSpacing(16);
        });
    }
}
