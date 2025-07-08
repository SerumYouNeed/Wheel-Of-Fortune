package com.serumyouneed.wheeloffortune;

import com.serumyouneed.wheeloffortune.database.DatabaseLoader;
import com.serumyouneed.wheeloffortune.model.*;
import com.serumyouneed.wheeloffortune.service.GameService;
import com.serumyouneed.wheeloffortune.service.UserService;
import com.serumyouneed.wheeloffortune.utils.CategorySelector;
import com.serumyouneed.wheeloffortune.utils.Messages;
import com.serumyouneed.wheeloffortune.utils.Printer;
import com.serumyouneed.wheeloffortune.utils.RealSleeper;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(scanner);
        User user = userService.startUser();
        Player player = new Player(1000, user);
        Wheel wheel = new Wheel(new RealSleeper());

        GameService game = new GameService(scanner, player, wheel, 1000);
        game.setCategory(CategorySelector.selectCategory());
        List<Guessable> puzzleList = DatabaseLoader.load(game.getCategory());

        puzzleListChecker(puzzleList);

        game.setPuzzle(new Puzzle(puzzleList.get(random.nextInt(0, puzzleList.size()))));

        gameLoop(game, random);

        scanner.close();

    }

    static void gameLoop (GameService game, Random random) {
        while (game.startGame()) {
            if (game.afterGoodPuzzleGuess()) {
                game.setCategory(CategorySelector.selectCategory());
                List<Guessable> puzzleList = DatabaseLoader.load(game.getCategory());
                puzzleListChecker(puzzleList);
                game.setPuzzle(new Puzzle(puzzleList.get(random.nextInt(0, puzzleList.size()))));
                continue;
            };
        }

    }

    /**
     * Check if list of puzzles is not empty.
     * @param list (List<Guessable>): List of puzzles.
     */
    static void puzzleListChecker(List<Guessable> list) {
        if (list == null || list.isEmpty()) {
            Printer.print(Messages.NO_PUZZLES);
        }
    }
}
