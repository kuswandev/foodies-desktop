package com.me.foodies.controllers;

import com.me.foodies.models.Menu;
import com.me.foodies.utils.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class MenuController {

    public ArrayList<Menu> getLatestMenus() {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT * FROM menus";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            ArrayList<Menu> menus = new ArrayList<>();
            while (result.next()) {
                Menu menu = new Menu(
                        result.getInt("id"),
                        result.getString("category"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getDouble("rating")
                );
                menus.add(menu);
            }
            return menus;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Menu getMenuById(int id) {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT * FROM menus WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Menu menu = new Menu(
                        result.getInt("id"),
                        result.getString("category"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getDouble("rating")
                );
                return menu;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Menu> getPopularMenus() {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT * FROM menus";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            ArrayList<Menu> menus = new ArrayList<>();
            while (result.next()) {
                Menu menu = new Menu(
                        result.getInt("id"),
                        result.getString("category"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getDouble("rating")
                );
                menus.add(menu);
            }
            Collections.shuffle(menus);
            return menus;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Menu> searchMenus(String name) {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT * FROM menus WHERE name ILIKE '%" + name + "%'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            ArrayList<Menu> menus = new ArrayList<>();
            while (result.next()) {
                Menu menu = new Menu(
                        result.getInt("id"),
                        result.getString("category"),
                        result.getString("description"),
                        result.getString("image"),
                        result.getString("name"),
                        result.getInt("price"),
                        result.getDouble("rating")
                );
                menus.add(menu);
            }
            return menus;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
