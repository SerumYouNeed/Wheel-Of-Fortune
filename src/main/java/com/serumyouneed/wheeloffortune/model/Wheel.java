package com.serumyouneed.wheeloffortune.model;

import com.serumyouneed.wheeloffortune.utils.Sleeper;

import java.util.Random;

/**
 * This class simulates spinning wheel.
 */
public class Wheel {

    private final Sleeper sleeper;

    public Wheel(Sleeper sleeper) {
        this.sleeper = sleeper;
    }

    /**
     * Function simulates fields of wheel with prises for uncovered letter as multiplier.
     * @param random      (int): Generate number simulating spin of the wheel.
     * @return multiplier (int): How many times will be multiplied each uncovered letter
     */
    public int switchToField(int random) {
        int multiplier = 0;
        switch (random) {
            case 1:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("Each letter is worth $1");
                multiplier = 1;
                break;
            case 2:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("Each letter is worth $10");
                multiplier = 10;
                break;
            case 3:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("Each letter is worth $25");
                multiplier = 25;
                break;
            case 4:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("Each letter is worth $50");
                multiplier = 50;
                break;
            case 5:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("Each letter is worth $100");
                multiplier = 100;
                break;
            case 6:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("Each letter is worth $200");
                multiplier = 200;
                break;
            default:
                System.out.println("Wheel of fortune is not spinning...");
        }
        return multiplier;
    }

    /**
     * Function generates random number between 1-7 (included)
     * @return field (int): Selected number
     */
    public int spinTheWheel() {
        Random random = new Random();
        int field;
        field = random.nextInt(1, 7);
        return  field;
    }
}