package com.serumyouneed.wheeloffortune.model;

/**
 * Supporting class to create Movie object. Title field will be used as a puzzle string to mask during the game.
 *
 */
public class Movie {
    private int id;
    private String title;
    private int releaseYear;

    public Movie(int id, String title, int releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    // Getter
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    // toString do debugowania
    @Override
    public String toString() {
        return title + " (" + releaseYear + ")";
    }
}
