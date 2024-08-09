package com.me.foodies.models;

import java.sql.Timestamp;

public class Order {
    public int id;
    public Timestamp createdAt;
    public int menuId;
    public int userId;
    public int amount;
    public int menuQuantity;
    public String paymentMethod;
    public String status;
    public Menu menu;

    public Order(int id, Timestamp createdAt, int menuId, int userId, int amount, int menuQuantity, String paymentMethod, String status, Menu menu) {
        this.id = id;
        this.createdAt = createdAt;
        this.menuId = menuId;
        this.userId = userId;
        this.amount = amount;
        this.menuQuantity = menuQuantity;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.menu = menu;
    }
}
