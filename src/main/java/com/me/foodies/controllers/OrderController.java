package com.me.foodies.controllers;

import com.me.foodies.models.Menu;
import com.me.foodies.models.Order;
import com.me.foodies.utils.Database;
import com.me.foodies.utils.Navigator;
import com.me.foodies.views.screens.FinishOrderScreen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderController {

    public void createOrder(int menuId, int userId, int amount, int menuQuantity, String paymentMethod, String status) {
        try {
            Connection connection = Database.getConnection();
            String query = "INSERT INTO orders (menu_id, user_id, amount, menu_quantity, payment_method, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, menuId);
            statement.setInt(2, userId);
            statement.setInt(3, amount);
            statement.setInt(4, menuQuantity);
            statement.setString(5, paymentMethod);
            statement.setString(6, status);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                int orderId = result.getInt(1);
                Navigator.replace(new FinishOrderScreen(orderId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(int orderId) {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT orders.id AS order_id, orders.created_at, orders.menu_id, orders.user_id, "
                    + "orders.amount, orders.menu_quantity, orders.payment_method, orders.status, "
                    + "menus.id AS menu_id, menus.category, menus.description, menus.image, menus.name, "
                    + "menus.price, menus.rating "
                    + "FROM orders "
                    + "JOIN menus ON orders.menu_id = menus.id "
                    + "WHERE orders.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Menu menu = new Menu(
                        result.getInt("menu_id"),
                        result.getString("category"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getDouble("rating")
                );

                Order order = new Order(
                        result.getInt("order_id"),
                        result.getTimestamp("created_at"),
                        result.getInt("menu_id"),
                        result.getInt("user_id"),
                        result.getInt("amount"),
                        result.getInt("menu_quantity"),
                        result.getString("payment_method"),
                        result.getString("status"),
                        menu
                );

                return order;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Order> getUserOrders(int userId) {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT orders.id AS order_id, orders.created_at, orders.menu_id, orders.user_id, "
                    + "orders.amount, orders.menu_quantity, orders.payment_method, orders.status, "
                    + "menus.id AS menu_id, menus.category, menus.description, menus.image, menus.name, "
                    + "menus.price, menus.rating "
                    + "FROM orders "
                    + "JOIN menus ON orders.menu_id = menus.id "
                    + "WHERE orders.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();

            ArrayList<Order> orders = new ArrayList<>();
            while (result.next()) {
                Menu menu = new Menu(
                        result.getInt("menu_id"),
                        result.getString("category"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getDouble("rating")
                );

                Order order = new Order(
                        result.getInt("order_id"),
                        result.getTimestamp("created_at"),
                        result.getInt("menu_id"),
                        result.getInt("user_id"),
                        result.getInt("amount"),
                        result.getInt("menu_quantity"),
                        result.getString("payment_method"),
                        result.getString("status"),
                        menu
                );

                orders.add(order);
            }
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        try {
            Connection connection = Database.getConnection();
            String query = "UPDATE orders SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
