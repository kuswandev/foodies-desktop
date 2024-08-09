package com.me.foodies.views.screens;

import com.me.foodies.controllers.OrderController;
import com.me.foodies.models.Order;
import com.me.foodies.models.User;
import com.me.foodies.utils.Navigator;
import com.me.foodies.utils.Session;
import com.me.foodies.views.components.Appbar;
import com.me.foodies.views.components.Sidebar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Objects;

public class OrderHistoryScreen extends HBox {
    OrderController orderController = new OrderController();
    Session session = Session.getInstance();

    public OrderHistoryScreen() {
        render();
    }

    private void render() {
        User user = session.getUser();
        ArrayList<Order> orders = orderController.getUserOrders(user.id);

        Appbar appbar = new Appbar("Order History");

        // Order List
        VBox orderList = new VBox(24);
        orders.forEach(order -> {
            Rectangle clip = new Rectangle(80, 80);
            clip.setArcHeight(24);
            clip.setArcWidth(24);
            ImageView menuImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/menus/" + order.menu.image))));
            menuImage.setFitWidth(80);
            menuImage.setPreserveRatio(true);
            menuImage.setClip(clip);

            Text menuName = new Text(order.menu.name);
            menuName.setStyle("-fx-font-size: 16; -fx-font-weight: bold");
            Text orderAmount = new Text("Rp." + order.amount);
            orderAmount.setStyle("-fx-fill: #0ea5e9; -fx-font-weight: bold");
            Text orderStatus = new Text("Status: " + order.status);

            VBox orderDetail = new VBox(8, menuName, orderAmount, orderStatus);
            HBox orderItem = new HBox(24, menuImage, orderDetail);
            orderItem.setStyle("-fx-background-color: #ffffff; -fx-padding: 8px");
            orderItem.setOnMouseClicked(mouseEvent -> {
                Navigator.navigate(new OrderDetailScreen(order.id));
            });

            orderList.getChildren().add(orderItem);
        });
        ScrollPane orderListSection = new ScrollPane(orderList);
        orderListSection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Main
        VBox main = new VBox(48, appbar, orderListSection);
        main.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 24px; -fx-padding: 48px");

        this.getChildren().addAll(new Sidebar(), main);
        this.setSpacing(24);
        this.setStyle("-fx-background-color: #e2e8f0; -fx-padding: 24px");
        HBox.setHgrow(main, Priority.ALWAYS);
    }
}
