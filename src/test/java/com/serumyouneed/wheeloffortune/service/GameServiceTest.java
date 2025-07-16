package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.database.DatabaseLoader;
import com.serumyouneed.wheeloffortune.model.*;
import com.serumyouneed.wheeloffortune.utils.CategorySelector;
import com.serumyouneed.wheeloffortune.utils.RealSleeper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private Random random;
    private Player player;
    private Wheel wheel;
    private Scanner scanner;
    private int price;

    @InjectMocks
    private GameService game;

    @BeforeEach
    void setUp() {
        game.setCategory(CategorySelector.selectCategory());

        List<Guessable> puzzleList = DatabaseLoader.load(game.getCategory());
        if (puzzleList == null || puzzleList.isEmpty()) {
            System.out.println("No movies available. Exiting the game.");
        }

        game.setPuzzle(new Puzzle(puzzleList.get(random.nextInt(0, puzzleList.size()))));
        game.startGame();

    }

    @AfterEach
    void atClose() {
        scanner.close();
    }

    @Test
    void testFoundLetterCounter() {
        String puzzle = "ABCDEFG";
        String input = "A";
        System.out.println("");
    }

    @Test
    void showOptionsAndProcessChoice() {
    }
}