package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.model.Movie;
import com.serumyouneed.wheeloffortune.model.Player;
import com.serumyouneed.wheeloffortune.model.Puzzle;
import com.serumyouneed.wheeloffortune.model.Wheel;
import com.serumyouneed.wheeloffortune.utils.RealSleeper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.serumyouneed.wheeloffortune.Main.loadFromDatabase;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @BeforeAll {
        Random random = new Random();

        List<Movie> movies = loadFromDatabase();
        if (movies == null || movies.isEmpty()) {
            System.out.println("No movies available. Exiting the game.");
        }

        Puzzle puzzle = new Puzzle(movies.get(random.nextInt(0, movies.size())));
        Player player = new Player(1000);
        Wheel wheel = new Wheel(new RealSleeper());
        Scanner scanner = new Scanner(System.in);

        GameService game = new GameService(scanner, puzzle, player, wheel, 1000);
        game.startGame();
    }

    @AfterAll {
        scanner.close();
    }

    @Test
    void startGame() {

    }

    @Test
    void showOptionsAndProcessChoice() {
    }
}