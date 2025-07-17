package com.serumyouneed.wheeloffortune.model;

/**
 * Supporting class to create Movie object. Title field will be used as a puzzle string to mask during the game.
 *
 */
public class Movie implements Guessable{
    private final int id;
    private final String title;
    private final int releaseYear;

    public Movie(int id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getText() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    // toString for debugging purpose
    @Override
    public String toString() {
        return title + " (" + releaseYear + ")";
    }
}
