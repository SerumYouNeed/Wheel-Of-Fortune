package com.serumyouneed.wheeloffortune.database;

import com.serumyouneed.wheeloffortune.dao.MovieDao;
import com.serumyouneed.wheeloffortune.dao.ProverbDao;
import com.serumyouneed.wheeloffortune.dao.TheDaos;
import com.serumyouneed.wheeloffortune.model.Guessable;
import com.serumyouneed.wheeloffortune.model.User;
import com.serumyouneed.wheeloffortune.utils.CategorySelector;
import com.serumyouneed.wheeloffortune.utils.Messages;
import com.serumyouneed.wheeloffortune.utils.Printer;

import java.sql.*;
import java.util.List;

public class DatabaseLoader {

    public static List<Guessable> load(CategorySelector.Category category) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            TheDaos dao;
            switch (category) {
                case MOVIE -> {
                    dao = new MovieDao(conn);
                }
                case PROVERB -> {
                    dao = new ProverbDao(conn);
                }
                default -> throw new IllegalStateException("Unexpected value: " + category);
            }
            return dao.getAll();
        } catch (Exception e) {
            Printer.print(Messages.ERROR_DATABASE + e.getMessage());
            return null;
        }
    }
    public void loadUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (nickname) VALUES (?)", Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, user.getNickname());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
            }

        } catch (SQLException e) {
            Printer.print(Messages.ERROR_CREATING_NEW_USER + e.getMessage());
        }
    }
}
