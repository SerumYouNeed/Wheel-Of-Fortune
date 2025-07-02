package com.serumyouneed.wheeloffortune.dao;

import com.serumyouneed.wheeloffortune.database.DatabaseConnection;
import com.serumyouneed.wheeloffortune.model.User;
import com.serumyouneed.wheeloffortune.utils.Messages;
import com.serumyouneed.wheeloffortune.utils.Printer;

import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao {

    public static User setNewUserToDatabase(String nickname) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (nickname) VALUES (?)", Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, nickname);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                User user = new User(nickname, false);
                user.setId(id);
                return user;
            }

        } catch (SQLException e) {
            Printer.print(Messages.ERROR_CREATING_NEW_USER + e.getMessage());
        }
        return null;
    }

    public static User searchUserInDatabase(String nickname) {
        if (nickname.isBlank() || nickname.length() > 20) {
            Printer.print(Messages.INVALID_NICKNAME);
            return null;
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT id FROM users WHERE nickname = ?"
            );
            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                User user = new User(nickname, false);
                user.setId(id);
                return user;
            } else {
                Printer.print(Messages.ERROR_USER_NOT_FOUND);
            }

        } catch (SQLException e) {
            Printer.print(Messages.ERROR_LOGGING + e.getMessage());
        }

        return null;
    }
}
