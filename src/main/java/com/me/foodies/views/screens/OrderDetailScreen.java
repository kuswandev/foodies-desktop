package com.me.foodies.views.screens;

import com.me.foodies.controllers.OrderController;
import com.me.foodies.models.Order;
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

public class OrderDetailScreen extends HBox {
    OrderController orderController = new OrderController();
    int orderId;

    public OrderDetailScreen(int id) {
        this.orderId = id;
        render();
    }

    private void render() {
        Order order = orderController.getOrderById(orderId);

        Appbar appbar = new Appbar("Order Detail");

        // Menu
        Text menuTitle = new Text("Menu Detail");
        menuTitle.setStyle("-fx-font-size: 18; -fx-font-weight: bold");

        Rectangle clip = new Rectangle(180, 180);
        clip.setArcHeight(24);
        clip.setArcWidth(24);
        ImageView menuImage = new ImageView(new Image(getClass().getResourceAsStream("/images/menus/" + order.menu.image)));
        menuImage.setFitWidth(180);
        menuImage.setPreserveRatio(true);
        menuImage.setClip(clip);

        Text menuName = new Text(order.menu.name);
        menuName.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Text menuCategory = new Text(order.menu.category);
        menuCategory.setStyle("-fx-font-size: 16px");
        Text menuRating = new Text("★" + order.menu.rating);
        Text menuPrice = new Text("Rp." + order.menu.price);
        menuPrice.setStyle("-fx-fill: #0ea5e9; -fx-font-size: 18px; -fx-font-weight: bold");
        VBox menuInfo = new VBox(8, menuName, menuCategory, menuRating, menuPrice);

        HBox menuDetail = new HBox(24, menuImage, menuInfo);
        VBox menuDetailSection = new VBox(16, menuTitle, menuDetail);

        // Order
        Text orderTitle = new Text("Order Detail");
        orderTitle.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
        Text orderAmount = new Text("Amount: Rp." + order.amount);
        orderAmount.setStyle("-fx-font-size: 14px");
        Text orderPaymentMethod = new Text("Payment: " + order.paymentMethod);
        orderPaymentMethod.setStyle("-fx-font-size: 14px");
        Text orderStatus = new Text("Status: " + order.status);
        orderStatus.setStyle("-fx-font-size: 14px");

        VBox orderDetail = new VBox(16, orderAmount, orderPaymentMethod, orderStatus);
        VBox orderDetailSection = new VBox(16, orderTitle, orderDetail);

        // Finish Order Button
        Button finishOrderButton = new Button("Finish Order");
        finishOrderButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #0ea5e9; -fx-padding: 12px 24px");
        finishOrderButton.setOnAction(actionEvent -> {
            orderController.updateOrderStatus(orderId, "Finished Order");
            Navigator.replace(new OrderDetailScreen(orderId));
        });
        VBox finishOrderButtonSection = new VBox(finishOrderButton);
        finishOrderButtonSection.setAlignment(Pos.BOTTOM_RIGHT);
        VBox.setVgrow(finishOrderButtonSection, Priority.ALWAYS);

        // Main
        VBox main = new VBox(48, appbar, menuDetailSection, orderDetailSection, finishOrderButtonSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
