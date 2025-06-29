package com.serumyouneed.wheeloffortune;

import com.serumyouneed.wheeloffortune.dao.MovieDao;
import com.serumyouneed.wheeloffortune.dao.ProverbDao;
import com.serumyouneed.wheeloffortune.dao.TheDaos;
import com.serumyouneed.wheeloffortune.database.DatabaseConnection;
import com.serumyouneed.wheeloffortune.model.*;
import com.serumyouneed.wheeloffortune.service.GameService;
import com.serumyouneed.wheeloffortune.utils.CategorySelector;
import com.serumyouneed.wheeloffortune.utils.RealSleeper;

import java.sql.Connection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        CategorySelector.Category category = CategorySelector.selectCategory();

        List<Guessable> puzzleList = loadFromDatabase(category);

        if (puzzleList == null || puzzleList.isEmpty()) {
            System.out.println("No movies available. Exiting the game.");
            return;
        }

        Puzzle puzzle = new Puzzle(puzzleList.get(random.nextInt(0, puzzleList.size())));
        Player player = new Player(1000);
        Wheel wheel = new Wheel(new RealSleeper());
        Scanner scanner = new Scanner(System.in);


        GameService game = new GameService(scanner, category, puzzle, player, wheel, 1000);
        while (game.startGame()) {
            if (game.afterGoodPuzzleGuess()) {
                category = CategorySelector.selectCategory();
                puzzleList = loadFromDatabase(category);
                puzzle = new Puzzle(puzzleList.get(random.nextInt(0, puzzleList.size())));
                game = new GameService(scanner, category, puzzle, player, wheel, 1000);
                game.startGame();
            };
        }

        scanner.close();
    }

    public static List<Guessable> loadFromDatabase(CategorySelector.Category category) {
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
