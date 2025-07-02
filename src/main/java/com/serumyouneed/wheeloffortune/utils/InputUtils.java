package com.serumyouneed.wheeloffortune.utils;

import java.util.Scanner;

/**
 *  Utility class for getting input from user and transform it.
 */
public class InputUtils {

    public static String readUppercaseInput(Scanner scanner) {
        return scanner.nextLine().trim().toUpperCase();
    }
}
