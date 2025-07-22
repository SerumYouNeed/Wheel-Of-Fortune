package com.serumyouneed.wheeloffortune.utils;

import java.util.Random;

/**
 * Utility class for selecting puzzle's category.
 */
public class CategorySelector {

    public enum Category {
        MOVIE,
        PROVERB
    }

    /**
     * Select category based on random number generated.
     * @return (Category)
     */
    public static Category selectCategory() {
        Category[] categories = Category.values();
        Random random = new Random();
        int index = random.nextInt(categories.length);
        return categories[index];
    }
}
