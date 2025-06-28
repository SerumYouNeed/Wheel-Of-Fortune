package com.serumyouneed.wheeloffortune.dao;

import com.serumyouneed.wheeloffortune.model.Guessable;
import com.serumyouneed.wheeloffortune.model.Proverb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProverbDao implements TheDaos{
    private final Connection conn;

    public ProverbDao(Connection conn) {
        this.conn = conn;
    }

    public List<Guessable> getAll() {
        String sql = "SELECT id, proverb, meaning FROM proverbs";
        List<Guessable> proverbs = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                proverbs.add(new Proverb(
                        rs.getInt("id"),
                        rs.getString("proverb"),
                        rs.getString("meaning")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while downloading proverb: " + e.getMessage());
        }

        return proverbs;
    }
}
