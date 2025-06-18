package com.serumyouneed.wheeloffortune.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static com.serumyouneed.wheeloffortune.Main.loadFromDatabase;
import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test
    void setPuzzle() {
        Puzzle puzzle = new Puzzle(new Movie(1, "Snow white", 1950));
        String expected = "Snow white";
        String actual = puzzle.getPuzzle();
        assertEquals(expected, actual);
    }

    @Test
    void setMaskedPuzzle() {
        Puzzle puzzle = new Puzzle(new Movie(1, "Snow white", 1950));
        puzzle.setMaskedPuzzle(puzzle.getPuzzle());
        String expected = "____ _____";
        String actual = puzzle.getMaskedPuzzle();
        assertEquals(expected, actual);
    }

    @Test
    void newDisplayCapitalLetter() {
        Puzzle puzzle = new Puzzle(new Movie(1, "Snow white", 1950));
        puzzle.setMaskedPuzzle(puzzle.getPuzzle());
        String input = "S";
        String expected = "S___ _____";
        String actual = puzzle.newDisplay(puzzle.getMaskedPuzzle(), puzzle.getPuzzle(), input);
        assertEquals(expected, actual);
    }

    @Test
    void newDisplayLowerLetter() {
        Puzzle puzzle = new Puzzle(new Movie(1, "Snow white", 1950));
        puzzle.setMaskedPuzzle(puzzle.getPuzzle());
        String input = "w";
        String expected = "___W W____";
        String actual = puzzle.newDisplay(puzzle.getMaskedPuzzle(), puzzle.getPuzzle(), input);
        assertEquals(expected, actual);
    }

    @Test
    void maskingProverb() {
        String proverb = "ALE FAJNA TA JAVA";
        String actual = Puzzle.maskingProverb(proverb);
        String expected = "___ _____ __ ____";
        assertEquals(expected, actual);
    }
}