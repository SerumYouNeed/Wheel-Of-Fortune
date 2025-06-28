package com.serumyouneed.wheeloffortune.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProverbTest {

    Proverb proverb = new Proverb(1,"XYZ", "ABC");

    @Test
    void creatingProverbNotNull() {
        assertNotNull(proverb);
    }

    @Test
    void checkProverbMeaning() {
        String actual = proverb.getMeaning();
        String expected = "ABC";
        assertEquals(expected, actual);
    }

    @Test
    void checkProverbPuzzle() {
        String actual = proverb.getText();
        String expected = "XYZ";
        assertEquals(expected, actual);
    }

    @Test
    void checkProverbIndex() {
        int actual = proverb.getId();
        int expected = 1;
        assertEquals(expected, actual);
    }

}