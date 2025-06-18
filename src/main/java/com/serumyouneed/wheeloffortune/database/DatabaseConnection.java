package com.serumyouneed.wheeloffortune.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    private static final String PROPERTIES_FILE = "/config.properties";

    public static Connection getConnection() {
        try (InputStream input = DatabaseConnection.class.getResourceAsStream(PROPERTIES_FILE)) {
            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
