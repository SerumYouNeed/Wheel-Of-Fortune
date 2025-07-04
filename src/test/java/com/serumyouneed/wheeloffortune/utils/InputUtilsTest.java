package com.serumyouneed.wheeloffortune.utils;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputUtilsTest {

    @Test
    void readUppercaseInputTrimWhiteSpacesBeforeAndAfter() {
        String scannerInput = " abc ";
        Scanner scanner = new Scanner(scannerInput);
        String expected = "ABC";
        String actual = InputUtils.readUppercaseInput(scanner);
        assertEquals(expected, actual);
    }

    @Test
    void readUppercaseInput() {
        String scannerInput = "zxy";
        Scanner scanner = new Scanner(scannerInput);
        String expected = "ZXY";
        String actual = InputUtils.readUppercaseInput(scanner);
        assertEquals(expected, actual);
    }
}