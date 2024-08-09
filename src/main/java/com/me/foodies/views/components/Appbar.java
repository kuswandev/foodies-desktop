package com.me.foodies.views.components;

import com.me.foodies.utils.Navigator;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Objects;

public class Appbar extends HBox {
    public Appbar(String title) {
        ImageView backIcon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons/arrow-left.png"))));
        backIcon.setFitHeight(24);
        backIcon.setPreserveRatio(true);
        backIcon.setOnMouseClicked(mouseEvent -> {
            Navigator.back();
        });

        Text pageTitle = new Text(title);
        pageTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");

        this.getChildren().addAll(backIcon, pageTitle);
        this.setSpacing(16);
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
