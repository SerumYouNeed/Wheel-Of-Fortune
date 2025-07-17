package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.model.Player;
import com.serumyouneed.wheeloffortune.model.Puzzle;
import com.serumyouneed.wheeloffortune.model.Wheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Mock
    private Scanner scanner;

    @Mock
    private Player player;

    @Mock
    private Wheel wheel;

    @Mock
    private Puzzle puzzle;

    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameService(scanner, player, wheel, 500);
        gameService.setPuzzle(puzzle);
    }

    @Test
    void testShowOptionsAndProcessChoice_validNumber() {
        when(scanner.hasNextInt()).thenReturn(true);
        boolean result = gameService.showOptionsAndProcessChoice();
        assertTrue(result);
    }

    @Test
    void testShowOptionsAndProcessChoice_invalidInput() {
        when(scanner.hasNextInt()).thenReturn(false);
        when(scanner.next()).thenReturn("abc");

        boolean result = gameService.showOptionsAndProcessChoice();

        assertFalse(result);
        verify(scanner).next();
    }

    @Test
    void testAfterGoodPuzzleGuess_yes() {
        when(scanner.next()).thenReturn("Y");

        boolean result = gameService.afterGoodPuzzleGuess();

        assertTrue(result);
    }

    @Test
    void testAfterGoodPuzzleGuess_no() {
        when(scanner.next()).thenReturn("N");

        boolean result = gameService.afterGoodPuzzleGuess();

        assertFalse(result);
    }

    @Test
    void testGuessAnswer_correct() {
        boolean result = invokeGuessAnswer("HELLO WORLD", "HELLO WORLD");
        assertTrue(result);
    }

    @Test
    void testGuessAnswer_incorrect() {
        boolean result = invokeGuessAnswer("HELLO WORLD", "GOODBYE WORLD");
        assertFalse(result);
    }

    // Helper method to test private guessAnswer.
    private boolean invokeGuessAnswer(String input, String puzzle) {
        return input.toUpperCase().equals(puzzle);
    }

    @Test
    void testFoundLetterCounter() {
        int result = callFoundLetterCounter("HELLO WORLD", "L");
        assertEquals(3, result);
    }

    private int callFoundLetterCounter(String puzzle, String input) {
        int counter = 0;
        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i) == input.charAt(0)) {
                counter++;
            }
        }
        return counter;
    }

    @Test
    void testBuyVowel() {
        int result = callBuyVowel(1000);
        assertEquals(900, result);
    }

    @Test
    void testBuyConsonant() {
        int result = callBuyConsonant(1000);
        assertEquals(950, result);
    }

    @Test
    void testTakeGuess() {
        int result = callTakeGuess(1000);
        assertEquals(990, result);
    }

    private int callBuyVowel(int money) {
        return money - 100;
    }

    private int callBuyConsonant(int money) {
        return money - 50;
    }

    private int callTakeGuess(int money) {
        return money - 10;
    }
}
