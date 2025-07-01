package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.database.DatabaseLoader;
import com.serumyouneed.wheeloffortune.model.*;
import com.serumyouneed.wheeloffortune.utils.CategorySelector;
import com.serumyouneed.wheeloffortune.utils.RealSleeper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


class GameServiceTest {

    static {
        Random random = new Random();

        Player player = new Player(1000);
        Wheel wheel = new Wheel(new RealSleeper());
        Scanner scanner = new Scanner(System.in);
        GameService game = new GameService(scanner, player, wheel, 1000);
        game.setCategory(CategorySelector.selectCategory());

        List<Guessable> puzzleList = DatabaseLoader.load(game.getCategory());
        if (puzzleList == null || puzzleList.isEmpty()) {
            System.out.println("No movies available. Exiting the game.");
        }

        game.setPuzzle(new Puzzle(puzzleList.get(random.nextInt(0, puzzleList.size()))));
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