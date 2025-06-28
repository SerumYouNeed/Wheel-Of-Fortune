package com.serumyouneed.wheeloffortune.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategorySelectorTest {

    @Test
    void testSelectCategoryReturnsValidEnum() {
        CategorySelector.Category newCategory = CategorySelector.selectCategory();
        assertTrue(List.of(CategorySelector.Category.values()).contains(newCategory),
                "Selected category should be a valid enum constant");
    }

    @Test
    public void testSelectCategoryIsNotNull() {
        CategorySelector.Category selected = CategorySelector.selectCategory();
        assertNotNull(selected, "Category should not be null");
    }

}