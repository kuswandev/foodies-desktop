package com.me.foodies.models;

public class Menu {
    public int id;
    public String category;
    public String description;
    public String image;
    public String name;
    public int price;
    public double rating;

    public Menu(int id, String category, String description, String image, String name, int price, double rating) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
}
