package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.model.*;
import com.serumyouneed.wheeloffortune.utils.CategorySelector;
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

    static {
        Random random = new Random();

        CategorySelector.Category category = CategorySelector.selectCategory();

        List<Guessable> puzzleList = loadFromDatabase(category);
        if (puzzleList == null || puzzleList.isEmpty()) {
            System.out.println("No movies available. Exiting the game.");
        }

        Puzzle puzzle = new Puzzle(puzzleList.get(random.nextInt(0, puzzleList.size())));
        Player player = new Player(1000);
        Wheel wheel = new Wheel(new RealSleeper());
        Scanner scanner = new Scanner(System.in);

        GameService game = new GameService(scanner, CategorySelector.selectCategory(), puzzle, player, wheel, 1000);
        game.startGame();

        scanner.close();
    }

    @Test
    void startGame() {

    }

    @Test
    void showOptionsAndProcessChoice() {
    }
}