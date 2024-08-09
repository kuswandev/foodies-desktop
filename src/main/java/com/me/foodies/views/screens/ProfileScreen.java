package com.me.foodies.views.screens;

import com.me.foodies.controllers.AddressController;
import com.me.foodies.models.User;
import com.me.foodies.utils.Session;
import com.me.foodies.views.components.Appbar;
import com.me.foodies.views.components.Sidebar;
import com.me.foodies.views.components.UserAddress;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Objects;

public class ProfileScreen extends HBox {
    AddressController addressController = new AddressController();
    Session session = Session.getInstance();

    public ProfileScreen() {
        render();
    }

    private void render() {
        User user = session.getUser();

        Appbar appbar = new Appbar("Profile");

        // User Detail
        Rectangle clip = new Rectangle(160, 160);
        clip.setArcHeight(24);
        clip.setArcWidth(24);
        ImageView userImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/users/user-1.png"))));
        userImage.setFitWidth(160);
        userImage.setPreserveRatio(true);
        userImage.setClip(clip);

        Text userName = new Text(user.name);
        userName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Text userEmail = new Text("john.doe@gmail.com");
        userEmail.setStyle("-fx-font-size: 16px");

        VBox userInfo = new VBox(8, userName, userEmail);
        HBox userInfoSection = new HBox(24, userImage, userInfo);

        // User Address
        Text addressTitle = new Text("Address");
        addressTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");

        TextField addressField = new TextField();
        addressField.setStyle("-fx-min-width: 480px; -fx-padding: 12px 16px");

        Button addAddressButton = new Button("Add Address");
        addAddressButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9; -fx-padding: 12px 24px");
        addAddressButton.setOnAction(actionEvent -> {
            int userId = user.id;
            String address = addressField.getText();
            addressController.addAddress(userId, address);
        });

        HBox addressForm = new HBox(24, addressField, addAddressButton);

        UserAddress userAddress = new UserAddress();
        VBox userAddressesSection = new VBox(24, addressTitle, addressForm, userAddress);

        // Main
        VBox main = new VBox(48, appbar, userInfoSection, userAddressesSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
