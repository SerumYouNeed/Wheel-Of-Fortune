package com.serumyouneed.wheeloffortune.model;

/**
 * Supporting class to create Proverb object. Proverb field will be used as a puzzle string to mask during the game.
 *
 */
public class Proverb implements Guessable{
    private int id;
    private String proverb;
    private String meaning;

    public Proverb(int id, String proverb, String meaning) {
        this.id = id;
        this.proverb = proverb;
        this.meaning = meaning;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getText() {
        return proverb;
    }

    public String getMeaning() {
        return meaning;
    }

    // toString for debugging purpose
    @Override
    public String toString() {
        return proverb + ": " + meaning;
    }
}
