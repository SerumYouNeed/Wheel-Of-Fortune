package com.serumyouneed.wheeloffortune.dao;

import com.serumyouneed.wheeloffortune.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProverbDao {
    private final Connection conn;

    public ProverbDao(Connection conn) {
        this.conn = conn;
    }

    public List<Movie> getAllProverbs() {
        String sql = "SELECT id, proverb, meaning";
        List<Movie> proverbs = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                proverbs.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("proverb"),
                        rs.getInt("meaning")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while downloading movies: " + e.getMessage());
        }

        return proverbs;
    }
}
