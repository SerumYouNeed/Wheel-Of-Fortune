package com.serumyouneed.wheeloffortune.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    Movie movie = new Movie(2, "ADA", 1986);

    @Test
    void creatingMovieNotNull() {
        assertNotNull(movie);
    }

    @Test
    void checkMovieReleaseYear() {
        int actual = movie.getReleaseYear();
        int expected = 1986;
        assertEquals(expected, actual);
    }

    @Test
    void checkMovieTitle() {
        String actual = movie.getText();
        String expected = "ADA";
        assertEquals(expected, actual);
    }

    @Test
    void checkMovieIndex() {
        int actual = movie.getId();
        int expected = 2;
        assertEquals(expected, actual);
    }

}