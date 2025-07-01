package com.serumyouneed.wheeloffortune.database;

import com.serumyouneed.wheeloffortune.dao.MovieDao;
import com.serumyouneed.wheeloffortune.dao.ProverbDao;
import com.serumyouneed.wheeloffortune.dao.TheDaos;
import com.serumyouneed.wheeloffortune.model.Guessable;
import com.serumyouneed.wheeloffortune.utils.CategorySelector;

import java.sql.Connection;
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
            System.out.println("Database error: " + e.getMessage());
            return null;
        }
    }
}
