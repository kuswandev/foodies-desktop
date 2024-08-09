package com.me.foodies.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/foodies_db";
        String user = "your_database_user";
        String password = "your_database_password";

        return DriverManager.getConnection(url, user, password);
    }
}
