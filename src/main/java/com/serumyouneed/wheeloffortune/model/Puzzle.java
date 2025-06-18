package com.serumyouneed.wheeloffortune.model;

public class Puzzle {

    private String puzzle;
    private String maskedPuzzle;
    private String partiallyMaskedPuzzle;

    public Puzzle(Movie movie) {
        this.puzzle = movie.getTitle().toUpperCase();
        this.maskedPuzzle = maskingProverb(movie.getTitle().toUpperCase());
        this.partiallyMaskedPuzzle = maskingProverb(movie.getTitle().toUpperCase());
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle.toUpperCase();
    }

    public void setMaskedPuzzle(String puzzle) {
        this.maskedPuzzle = maskingProverb(puzzle);
    }

    public String getMaskedPuzzle() {
        return maskedPuzzle;
    }

    public String getPuzzle() {
        return puzzle;
    }

    /**
     * Function uncover masked field if player input is in puzzle.
     * @param maskedPuzzle (String): Actual state of masked puzzle in display.
     * @param puzzle       (String): Actual puzzle from database set before round.
     * @param input        (String): Player's guessed letter.
     * @return updated     (String): Modified masked puzzle after letter checking.
     */
    public String newDisplay (String maskedPuzzle, String puzzle, String input) {
        input = input.toUpperCase();
        StringBuilder updated = new StringBuilder(maskedPuzzle);
        boolean found = false;

        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i) == input.charAt(0)) {
                updated.setCharAt(i, input.charAt(0));
                found = true;
            }
        }

        if (!found) {
            System.out.printf("We don't have \"%s\". Try again.%n", input);
        }

        return updated.toString();
    }

    /**
     * Function masking an input string
     * @param proverb (String)
     * @return masked (String): Letters covered by '_'. Spaces stays.
     */
    static String maskingProverb(String proverb) {
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < proverb.length(); i++) {
            if (proverb.charAt(i) == ' ') {
                masked.append(' ');
            } else {
                masked.append('_');
            }
        }
        return masked.toString();
    }

    public String getPartiallyMaskedPuzzle() {
        return partiallyMaskedPuzzle;
    }

    public void setPartiallyMaskedPuzzle(String partiallyMaskedPuzzle, String puzzle, String input) {
        this.partiallyMaskedPuzzle = newDisplay(partiallyMaskedPuzzle, puzzle, input);
    }
}
