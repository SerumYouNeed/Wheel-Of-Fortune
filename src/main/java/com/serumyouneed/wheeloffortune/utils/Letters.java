package com.serumyouneed.wheeloffortune.utils;

public enum Letters {
    VOWELS("AEIOU"),
    CONSONANTS("BCDFGHJKLMNPQRSTVWXYZ");

    private final String letters;

    Letters(String letters) {
        this.letters = letters;
    }

    public String getLetters() {
        return letters;
    }


}
