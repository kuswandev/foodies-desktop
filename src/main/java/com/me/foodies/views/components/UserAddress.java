package com.me.foodies.views.components;

import com.me.foodies.controllers.AddressController;
import com.me.foodies.models.Address;
import com.me.foodies.models.User;
import com.me.foodies.utils.Session;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class UserAddress extends VBox {
    AddressController addressController = new AddressController();
    Session session = Session.getInstance();

    public UserAddress() {
        User user = session.getUser();
        ArrayList<Address> addresses = addressController.getUserAddresses(user.id);

        addresses.forEach(address -> {
            Text addressText = new Text(address.address);
            Button deleteAddressButton = new Button("Delete");
            deleteAddressButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9");
            deleteAddressButton.setOnAction(actionEvent -> {
                addressController.deleteAddress(address.id);
            });

            HBox addressItem = new HBox(24, addressText, deleteAddressButton);
            addressItem.setAlignment(Pos.CENTER_LEFT);
            this.getChildren().add(addressItem);
        });

        this.setSpacing(8);
    }
}
