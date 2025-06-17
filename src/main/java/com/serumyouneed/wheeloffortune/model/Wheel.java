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
     * @param random:
     *              Generate number simulating spin of the wheel.
     * @return (int) multiplier
     */
    public int switchToField(int random) {
        int multiplier = 0;
        switch (random) {
            case 1:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("BANKRUPT!!! YOU LOST ALL OF YOUR MONEY...");
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
            case 7:
                System.out.println("Wheel is spinning...");
                sleeper.sleep();
                System.out.println("THAT IS NOT FAIR but each uncovered letter is worth $-100");
                multiplier = -100;
                break;
            default:
                System.out.println("Wheel of fortune is not spinning...");
        }
        return multiplier;
    }

    /**
     * Function generates random number between 1-7 (included)
     * @return (int) switchToField(Random int)
     */
    public int spinTheWheel() {
        Random random = new Random();
        int field;
        field = random.nextInt(1, 8);
        return  switchToField(field);
    }
}