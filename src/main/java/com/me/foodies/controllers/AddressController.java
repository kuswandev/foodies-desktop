package com.me.foodies.controllers;

import com.me.foodies.models.Address;
import com.me.foodies.utils.Database;
import com.me.foodies.utils.Navigator;
import com.me.foodies.views.screens.ProfileScreen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddressController {

    public void addAddress(int userId, String address) {
        try {
            Connection connection = Database.getConnection();
            String query = "INSERT INTO addresses (user_id, address) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, address);
            statement.executeUpdate();

            Navigator.replace(new ProfileScreen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(int id) {
        try {
            Connection connection = Database.getConnection();
            String query = "DELETE FROM addresses WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

            Navigator.replace(new ProfileScreen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Address> getUserAddresses(int userId) {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT * FROM addresses WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();

            ArrayList<Address> addresses = new ArrayList<>();
            while (result.next()) {
                Address address = new Address(
                        result.getInt("id"),
                        result.getString("address")
                );
                addresses.add(address);
            }
            return addresses;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
