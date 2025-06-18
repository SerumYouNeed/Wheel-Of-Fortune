package com.serumyouneed.wheeloffortune;

import com.serumyouneed.wheeloffortune.dao.MovieDao;
import com.serumyouneed.wheeloffortune.database.DatabaseConnection;
import com.serumyouneed.wheeloffortune.model.Movie;
import com.serumyouneed.wheeloffortune.model.Player;
import com.serumyouneed.wheeloffortune.model.Puzzle;
import com.serumyouneed.wheeloffortune.model.Wheel;
import com.serumyouneed.wheeloffortune.utils.RealSleeper;

import java.sql.Connection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        Random random = new Random();

        List<Movie> movies = loadFromDatabase();
        Puzzle puzzle = new Puzzle(movies.get(random.nextInt(0, movies.size())));

        Player player = new Player(1000);
        int prise = 10000;
        String input;

        Wheel fortuneWheel = new Wheel(new RealSleeper());

        Scanner scanner = new Scanner(System.in);

        boolean answer = false;
        while(!answer) {
            System.out.println();
            System.out.println(puzzle.getPartiallyMaskedPuzzle());
            System.out.println();
            int wheelReward = fortuneWheel.switchToField(fortuneWheel.spinTheWheel());
            if (wheelReward == 0) {
                balanceDisplay(player.getMoney());
                System.out.println("Bad luck... Try again later!");
            }
            System.out.print("Options: press 1 - buy a vowel ($100), 2 - buy a consonant ($50), 3 - write an answer ($10): ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Please, enter a vowel: ");
                    scanner.nextLine();
                    input = scanner.next().toUpperCase();
                    if (input.length() == 1 && "AEIOU".contains(input)) {
                        int foundVowels = foundLetterCounter(puzzle.getPuzzle(), input);
                        player.setMoney(buyVowel(player.getMoney()) + (foundVowels * wheelReward));
                        puzzle.setPartiallyMaskedPuzzle(puzzle.getPartiallyMaskedPuzzle(), puzzle.getPuzzle(), input);
                        balanceDisplay(player.getMoney());
                    } else {
                        System.out.println("That is not a vowel.");
                    }
                    break;
                }
                case 2 -> {
                    System.out.print("Please, enter a consonant: ");
                    scanner.nextLine();
                    input = scanner.nextLine().toUpperCase();
                    if (input.length() == 1 && "BCDFGHJKLMNPQRSTVWXYZ".contains(input.toUpperCase())) {
                        int foundConsonant = foundLetterCounter(puzzle.getPuzzle(), input);
                        player.setMoney(buyConsonant(player.getMoney()) + (foundConsonant * wheelReward));
                        puzzle.setPartiallyMaskedPuzzle(puzzle.getPartiallyMaskedPuzzle(), puzzle.getPuzzle(), input);
                        balanceDisplay(player.getMoney());
                    } else {
                        System.out.println("That is not a consonant.");
                    }
                    break;
                }
                case 3 -> {
                    player.setMoney(takeGuess(player.getMoney()));
                    System.out.print("Your guess is: ");
                    scanner.nextLine();
                    input = scanner.nextLine().toUpperCase();
                    answer = guessingProverb(input, puzzle.getPuzzle());
                    if (answer) {
                        player.setMoney(player.getMoney() + prise);
                        balanceDisplay(player.getMoney());
                    }
                    break;
                }
            }
        }
        scanner.close();
    }
    static void balanceDisplay (int playerMoney) {
        System.out.println();
        System.out.println("*******************");
        System.out.println("Your money: $" + playerMoney);
        System.out.println("*******************");
    }
    static boolean guessingProverb(String input, String proverb) {
        if (input.toUpperCase().equals(proverb)) {
            System.out.println("CORRECT!!! YOU WON!!!");
            return true;
        } else {
            System.out.println("WRONG");
            return false;
        }
    }

    static int buyVowel (int money) {
        return money - 100;
    }
    static int buyConsonant (int money) {
        return money - 50;
    }
    static int takeGuess (int money) {
        return money - 10;
    }
    static int foundLetterCounter (String proverb, String input) {
        int counter = 0;
        for (int i = 0; i < proverb.length(); i++) {
            if (proverb.charAt(i) == input.charAt(0)) {
                counter += 1;
            }
        }
        return counter;
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
