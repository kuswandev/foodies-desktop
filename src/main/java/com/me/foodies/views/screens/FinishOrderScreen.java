package com.me.foodies.views.screens;

import com.me.foodies.controllers.OrderController;
import com.me.foodies.models.Order;
import com.me.foodies.utils.Navigator;
import com.me.foodies.views.components.Appbar;
import com.me.foodies.views.components.Sidebar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FinishOrderScreen extends HBox {
    OrderController orderController = new OrderController();
    int orderId;

    public FinishOrderScreen(int id) {
        orderId = id;
        render();
    }

    private void render() {
        Order order = orderController.getOrderById(orderId);

        Appbar appbar = new Appbar("Finish Order");

        // Order Amount
        Text orderAmountLabel = new Text("Order Amount:");
        orderAmountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
        Text orderAmount = new Text("Rp." + order.amount);
        orderAmount.setStyle("-fx-fill: #0ea5e9; -fx-font-size: 24px; -fx-font-weight: bold");
        VBox orderAmountBox = new VBox(8, orderAmountLabel, orderAmount);
        orderAmountBox.setAlignment(Pos.CENTER);

        // Payment Method
        Text paymentMethodLabel = new Text("Payment Method:");
        paymentMethodLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
        Text paymentMethod = new Text(order.paymentMethod);
        paymentMethod.setStyle("-fx-fill: #0ea5e9; -fx-font-size: 16px; -fx-font-weight: bold");
        VBox paymentMethodBox = new VBox(8, paymentMethodLabel, paymentMethod);
        paymentMethodBox.setAlignment(Pos.CENTER);

        // Order Status
        Text orderStatusLabel = new Text("Status:");
        orderStatusLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
        Text orderStatus = new Text(order.status);
        orderStatus.setStyle("-fx-fill: #0ea5e9; -fx-font-size: 16px; -fx-font-weight: bold");
        VBox orderStatusBox = new VBox(8, orderStatusLabel, orderStatus);
        orderStatusBox.setAlignment(Pos.CENTER);


        // Order Action
        Button alreadyPaidButton = new Button("Already Paid");
        alreadyPaidButton.setStyle("-fx-padding: 12px 24px");
        alreadyPaidButton.setOnAction(actionEvent -> {
            orderController.updateOrderStatus(orderId, "Already Paid");
            Navigator.replace(new FinishOrderScreen(orderId));
        });
        Button goHomeButton = new Button("Go Home");
        goHomeButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9; -fx-padding: 12px 24px");
        goHomeButton.setOnAction(actionEvent -> {
            Navigator.replace(new HomeScreen());
        });

        HBox orderActions = new HBox(24);
        if (order.status.equals("Already Paid")) {
            orderActions.getChildren().add(goHomeButton);
        } else {
            orderActions.getChildren().addAll(alreadyPaidButton, goHomeButton);
        }
        orderActions.setAlignment(Pos.CENTER);

        VBox orderDetailSection = new VBox(48, orderAmountBox, paymentMethodBox, orderStatusBox, orderActions);
        orderDetailSection.setAlignment(Pos.CENTER);
        VBox.setVgrow(orderDetailSection, Priority.ALWAYS);

        // Main
        VBox main = new VBox(appbar, orderDetailSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
