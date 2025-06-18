package com.serumyouneed.wheeloffortune.dao;

import com.serumyouneed.wheeloffortune.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    private final Connection conn;

    public MovieDao(Connection conn) {
        this.conn = conn;
    }

    public List<Movie> getAllMovies() {
        String sql = "SELECT id, title, release_year FROM disney_animated_movies";
        List<Movie> movies = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("release_year")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error while downloading movies: " + e.getMessage());
        }

        return movies;
    }
}
