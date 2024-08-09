package com.me.foodies.views.screens;

import com.me.foodies.controllers.AddressController;
import com.me.foodies.controllers.MenuController;
import com.me.foodies.controllers.OrderController;
import com.me.foodies.models.Address;
import com.me.foodies.models.Menu;
import com.me.foodies.models.User;
import com.me.foodies.utils.Session;
import com.me.foodies.views.components.Appbar;
import com.me.foodies.views.components.Sidebar;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Objects;

public class OrderMenuScreen extends HBox {
    AddressController addressController = new AddressController();
    MenuController menuController = new MenuController();
    OrderController orderController = new OrderController();
    Session session = Session.getInstance();
    int menuId;

    public OrderMenuScreen(int id) {
        menuId = id;
        render();
    }

    private void render() {
        User user = session.getUser();
        Menu menu = menuController.getMenuById(menuId);
        ArrayList<Address> addresses = addressController.getUserAddresses(user.id);
        if (addresses.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please add your address before ordering menu");
            alert.showAndWait();
        }
        ArrayList<String> payments = new ArrayList<>();
        payments.add("Cash");
        payments.add("Bank Transfer");
        payments.add("GoPay");
        payments.add("ShopeePay");
        payments.add("Dana");

        Appbar appbar = new Appbar("Order Menu");

        // Menu
        Text menuLabel = new Text("Menu");
        menuLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");

        Rectangle clip = new Rectangle(180, 180);
        clip.setArcHeight(24);
        clip.setArcWidth(24);
        ImageView menuImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menus/" + menu.image))));
        menuImage.setFitWidth(180);
        menuImage.setPreserveRatio(true);
        menuImage.setClip(clip);

        Text menuName = new Text(menu.name);
        menuName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Text menuPrice = new Text("Rp." + menu.price);
        menuPrice.setStyle("-fx-fill: #0ea5e9; -fx-font-size: 18px; -fx-font-weight: bold");
        VBox menuMainInfo = new VBox(8, menuName, menuPrice);

        // Quantity
        Text quantityLabel = new Text("Order Count:");
        Text quantityAmount = new Text("Amount: Rp." + menu.price);
        quantityAmount.setStyle("-fx-fill: #0ea5e9; -fx-font-size: 18px; -fx-font-weight: bold");
        TextField quantityField = new TextField();
        quantityField.setText("1");
        quantityField.setAlignment(Pos.CENTER);
        quantityField.setStyle("-fx-max-width: 64px; -fx-padding: 8px");
        quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(newValue, "")) {
                quantityAmount.setText("Amount: Rp." + Integer.parseInt(newValue) * menu.price);
            }
        });
        HBox quantityAmountSection = new HBox(24, quantityField, quantityAmount);
        quantityAmountSection.setAlignment(Pos.CENTER);
        VBox quantityOrder = new VBox(8, quantityLabel, quantityAmountSection);

        VBox menuInfo = new VBox(24, menuMainInfo, quantityOrder);
        HBox menuDetail = new HBox(24, menuImage, menuInfo);
        VBox menuSection = new VBox(8, menuLabel, menuDetail);

        // Address
        Text addressLabel = new Text("Address");
        addressLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
        ComboBox<String> addressList = new ComboBox<>();
        addresses.forEach(address -> {
            addressList.getItems().add(address.address);
        });
        addressList.setValue(addresses.getFirst().address);
        addressList.setStyle("-fx-padding: 8px");
        VBox addressSection = new VBox(8, addressLabel, addressList);

        // Payment
        Text paymentLabel = new Text("Payment");
        paymentLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
        ComboBox<String> paymentList = new ComboBox<>();
        paymentList.getItems().addAll(payments);
        paymentList.setValue(payments.getFirst());
        paymentList.setStyle("-fx-padding: 8px");
        VBox paymentSection = new VBox(8, paymentLabel, paymentList);

        // Continue Order
        Button continueOrderButton = new Button("Continue Order");
        continueOrderButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9; -fx-padding: 12px 24px");
        continueOrderButton.setOnAction(actionEvent -> {
            int menuId = menu.id;
            int userId = user.id;
            int amount = menu.price * Integer.parseInt(quantityField.getText());
            int menuQuantity = Integer.parseInt(quantityField.getText());
            String paymentMethod = paymentList.getValue();
            String status = "Waiting Payment";
            orderController.createOrder(menuId, userId, amount, menuQuantity, paymentMethod, status);
        });
        HBox continueOrderSection = new HBox(continueOrderButton);
        continueOrderSection.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setVgrow(continueOrderSection, Priority.ALWAYS);

        // Main
        VBox main = new VBox(48, appbar, menuSection, addressSection, paymentSection, continueOrderSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
