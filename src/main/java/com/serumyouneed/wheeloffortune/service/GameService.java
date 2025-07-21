package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.utils.*;
import com.serumyouneed.wheeloffortune.model.Player;
import com.serumyouneed.wheeloffortune.model.Puzzle;
import com.serumyouneed.wheeloffortune.model.Wheel;

import java.util.Scanner;

import static com.serumyouneed.wheeloffortune.utils.InputUtils.readUppercaseInput;

/**
 * This is the game engine. Here all the logic of the game is combined.
 */
public class GameService {

    private CategorySelector.Category category;
    private Puzzle puzzle;

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public CategorySelector.Category getCategory() {
        return category;
    }

    public void setCategory(CategorySelector.Category category) {
        this.category = category;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    private final Scanner scanner;
    private final Player player;
    private final Wheel wheel;
    private final int price;

    public GameService(Scanner scanner, Player player, Wheel wheel, int price) {
        this.scanner = scanner;
        this.player = player;
        this.wheel = wheel;
        this.price = price;
    }

    public boolean startGame() {
        boolean answer = false;
        Printer.print(Messages.CATEGORY + category);
        while (!answer) {
            System.out.println();
            System.out.println(puzzle.getPartiallyMaskedPuzzle());
            System.out.println();
            int wheelReward = wheel.switchToField(wheel.spinTheWheel());
            if(showOptionsAndProcessChoice()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 0 -> answer = quitGame();
                    case 1 -> handleBuyVowel(wheelReward);
                    case 2 -> handleBuyConsonant(wheelReward);
                    case 3 -> answer = handleGuess(price);
                    default -> Printer.print(Messages.INVALID_OPTION);
                }
            }
        }
        return true;
    }

    public boolean quitGame() {
        Printer.print(Messages.GOODBYE);
        return true;
    }

    public boolean afterGoodPuzzleGuess() {
        boolean promptUser = true;
        boolean playAgain = false;
        while (promptUser) {
            Printer.print(Messages.PLAY_AGAIN);
            if (scanner.next().equalsIgnoreCase("Y")) {
                Printer.print(Messages.LETS_BEGIN);
                promptUser = false;
                playAgain = true;
            } else if (scanner.next().equalsIgnoreCase("N")) {
                Printer.print(Messages.GOODBYE);
                promptUser = false;
            } else {
                Printer.print(Messages.UNKNOWN_RESPONSE);;
            }
        }
        return playAgain;
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
        Printer.print(Messages.OPTIONS);
        if (!scanner.hasNextInt()) {
            Printer.print(Messages.ENTER_VALID_NUMBER);
            scanner.next();
            return false;
        }
        return true;
    }

    private void handleBuyVowel(int wheelReward) {
        Printer.print(Messages.ENTER_A_VOWEL);
        String input = readUppercaseInput(scanner);
        String vowels = Letters.VOWELS.getLetters();
        if (input.length() == 1 && vowels.contains(input)) {
            int foundVowels = foundLetterCounter(puzzle.getPuzzle(), input);
            player.setMoney(buyVowel(player.getMoney()) + (foundVowels * wheelReward));
            puzzle.setPartiallyMaskedPuzzle(puzzle.getPartiallyMaskedPuzzle(), puzzle.getPuzzle(), input);
            Printer.print(Messages.YOUR_MONEY + player.getMoney());
        } else {
            Printer.print(Messages.NOT_A_VOWEL);
        }
    }

    private void handleBuyConsonant(int wheelReward) {
        Printer.print(Messages.ENTER_A_CONSONANT);
        String input = readUppercaseInput(scanner);
        String consonants = Letters.CONSONANTS.getLetters();
        if (input.length() == 1 && consonants.contains(input.toUpperCase())) {
            int foundConsonant = foundLetterCounter(puzzle.getPuzzle(), input);
            player.setMoney(buyConsonant(player.getMoney()) + (foundConsonant * wheelReward));
            puzzle.setPartiallyMaskedPuzzle(puzzle.getPartiallyMaskedPuzzle(), puzzle.getPuzzle(), input);
            Printer.print(Messages.YOUR_MONEY + player.getMoney());
        } else {
            Printer.print(Messages.NOT_A_CONSONANT);
        }
    }

    private boolean handleGuess(int price) {
        player.setMoney(takeGuess(player.getMoney()));
        Printer.print(Messages.ENTER_GUESS);
        String input = readUppercaseInput(scanner);
        boolean answer = guessAnswer(input, puzzle.getPuzzle());
        if (answer) {
            player.setMoney(player.getMoney() + price);
            Printer.print(Messages.YOUR_MONEY + player.getMoney());
            return true;
        }
        return false;
    }

    private boolean guessAnswer(String input, String proverb) {
        if (input.toUpperCase().equals(proverb)) {
            Printer.print(Messages.CORRECT);
            return true;
        } else {
            Printer.print(Messages.WRONG);
            return false;
        }
    }

    private int takeGuess (int money) {
        return money - 10;
    }
}
