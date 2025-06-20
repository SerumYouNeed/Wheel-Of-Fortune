package com.serumyouneed.wheeloffortune;

import com.serumyouneed.wheeloffortune.dao.MovieDao;
import com.serumyouneed.wheeloffortune.database.DatabaseConnection;
import com.serumyouneed.wheeloffortune.model.Movie;
import com.serumyouneed.wheeloffortune.model.Player;
import com.serumyouneed.wheeloffortune.model.Puzzle;
import com.serumyouneed.wheeloffortune.model.Wheel;
import com.serumyouneed.wheeloffortune.service.GameService;
import com.serumyouneed.wheeloffortune.utils.RealSleeper;

import java.sql.Connection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        List<Movie> movies = loadFromDatabase();
        if (movies == null || movies.isEmpty()) {
            System.out.println("No movies available. Exiting the game.");
            return;
        }

        Puzzle puzzle = new Puzzle(movies.get(random.nextInt(0, movies.size())));
        Player player = new Player(1000);
        Wheel wheel = new Wheel(new RealSleeper());
        Scanner scanner = new Scanner(System.in);


        GameService game = new GameService(scanner, puzzle, player, wheel, 1000);
        game.startGame();

        scanner.close();
    }

    public static List<Movie> loadFromDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            MovieDao movieDao = new MovieDao(conn);
            return movieDao.getAllMovies();
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
            return null;
        }
    }
}
