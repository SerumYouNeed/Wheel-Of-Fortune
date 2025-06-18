package com.serumyouneed.wheeloffortune.utils;

public class RealSleeper implements Sleeper{

    /**
     * Function stops program for two seconds.
     */
    @Override
    public void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Wheel is stopped");
            Thread.currentThread().interrupt();
        }
    }
}
