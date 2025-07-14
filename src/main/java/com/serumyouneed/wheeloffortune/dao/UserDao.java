package com.serumyouneed.wheeloffortune.dao;

import com.serumyouneed.wheeloffortune.database.DatabaseConnection;
import com.serumyouneed.wheeloffortune.model.User;
import com.serumyouneed.wheeloffortune.utils.Messages;
import com.serumyouneed.wheeloffortune.utils.Printer;

import java.sql.*;


public class UserDao {

    public static boolean checkingNicknameUniqueness(String nickname) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT id FROM users WHERE nickname = ?"
            );
            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Printer.print(Messages.USED_NICKNAME);
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static User setNewUserToDatabase(String nickname, String hashedPassword) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (nickname, password_hash) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, nickname);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new User(nickname, hashedPassword, false);
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
                    "SELECT password_hash FROM users WHERE nickname = ?"
            );
            stmt.setString(1, nickname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                return new User(nickname, hashedPassword,false);
            } else {
                Printer.print(Messages.ERROR_USER_NOT_FOUND);
            }

        } catch (SQLException e) {
            Printer.print(Messages.ERROR_LOGGING + e.getMessage());
        }

        return null;
    }
}
