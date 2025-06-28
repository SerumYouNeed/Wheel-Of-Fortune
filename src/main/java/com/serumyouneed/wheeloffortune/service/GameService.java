package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.utils.CategorySelector;
import com.serumyouneed.wheeloffortune.model.Player;
import com.serumyouneed.wheeloffortune.model.Puzzle;
import com.serumyouneed.wheeloffortune.model.Wheel;

import java.util.Scanner;

public class GameService {
    private final Scanner scanner;
    private final CategorySelector.Category category;
    private final Puzzle puzzle;
    private final Player player;
    private final Wheel wheel;
    private final int price;

    public GameService(Scanner scanner, CategorySelector.Category category, Puzzle puzzle, Player player, Wheel wheel, int price) {
        this.scanner = scanner;
        this.category = category;
        this.puzzle = puzzle;
        this.player = player;
        this.wheel = wheel;
        this.price = price;
    }

    public void startGame() {
        boolean answer = false;
        System.out.println("CATEGORY: " + category);
        while (!answer) {
            System.out.println();
            System.out.println(puzzle.getPartiallyMaskedPuzzle());
            System.out.println();
            int wheelReward = wheel.switchToField(wheel.spinTheWheel());
            if(showOptionsAndProcessChoice()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> handleBuyVowel(wheelReward);
                    case 2 -> handleBuyConsonant(wheelReward);
                    case 3 -> answer = handleGuess(price);
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }

    private int foundLetterCounter (String proverb, String input) {
        int counter = 0;
        for (int i = 0; i < proverb.length(); i++) {
            if (proverb.charAt(i) == input.charAt(0)) {
                counter += 1;
            }
        }
        return counter;
    }

    private int buyVowel (int money) {
        return money - 100;
    }

    private int buyConsonant (int money) {
        return money - 50;
    }

    public boolean showOptionsAndProcessChoice() {
        System.out.print("Options: press 1 - buy a vowel ($100), 2 - buy a consonant ($50), 3 - write an answer ($10): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number (1, 2 or 3).");
            scanner.next();
            return false;
        }
        return true;
    }

    private void handleBuyVowel(int wheelReward) {
        System.out.print("Please, enter a vowel: ");
        scanner.nextLine();
        String input = readUppercaseInput(scanner);
        if (input.length() == 1 && "AEIOU".contains(input)) {
            int foundVowels = foundLetterCounter(puzzle.getPuzzle(), input);
            player.setMoney(buyVowel(player.getMoney()) + (foundVowels * wheelReward));
            puzzle.setPartiallyMaskedPuzzle(puzzle.getPartiallyMaskedPuzzle(), puzzle.getPuzzle(), input);
            displayBalance(player.getMoney());
        } else {
            System.out.println("That is not a vowel.");
        }
    }

    private void handleBuyConsonant(int wheelReward) {
        System.out.print("Please, enter a consonant: ");
        scanner.nextLine();
        String input = readUppercaseInput(scanner);
        if (input.length() == 1 && "BCDFGHJKLMNPQRSTVWXYZ".contains(input.toUpperCase())) {
            int foundConsonant = foundLetterCounter(puzzle.getPuzzle(), input);
            player.setMoney(buyConsonant(player.getMoney()) + (foundConsonant * wheelReward));
            puzzle.setPartiallyMaskedPuzzle(puzzle.getPartiallyMaskedPuzzle(), puzzle.getPuzzle(), input);
            displayBalance(player.getMoney());
        } else {
            System.out.println("That is not a consonant.");
        }
    }

    private boolean handleGuess(int prise) {
        player.setMoney(takeGuess(player.getMoney()));
        System.out.print("Your guess is: ");
        scanner.nextLine();
        String input = readUppercaseInput(scanner);
        boolean answer = guessAnswer(input, puzzle.getPuzzle());
        if (answer) {
            player.setMoney(player.getMoney() + prise);
            displayBalance(player.getMoney());
            return true;
        }
        return false;
    }

    private boolean guessAnswer(String input, String proverb) {
        if (input.toUpperCase().equals(proverb)) {
            System.out.println("CORRECT!!! YOU WON!!!");
            return true;
        } else {
            System.out.println("WRONG");
            return false;
        }
    }

    private int takeGuess (int money) {
        return money - 10;
    }


    private void displayBalance(int money) {
            System.out.println();
            System.out.println("*******************");
            System.out.println("Your money: $" + money);
            System.out.println("*******************");
    }

    private String readUppercaseInput(Scanner scanner) {
        return scanner.nextLine().trim().toUpperCase();
    }

    private CategorySelector.Category getCategory() {
        return category;
    }
}
