package com.serumyouneed.wheeloffortune.model;

import static org.mockito.Mockito.*;

import com.serumyouneed.wheeloffortune.utils.Sleeper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WheelTest {

    private Wheel wheel;
    private Sleeper mockSleeper;

    @BeforeEach
    void setUp() {
        mockSleeper = mock(Sleeper.class);
        wheel = new Wheel(mockSleeper);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void switchToFieldOne() {
        int actualResult = wheel.switchToField(1);
        assertEquals(1, actualResult);
    }

    @Test
    void switchToFieldTwo() {
        int actualResult = wheel.switchToField(2);
        assertEquals(10, actualResult);
    }

    @Test
    void switchToFieldThree() {
        int actualResult = wheel.switchToField(3);
        assertEquals(25, actualResult);
    }

    @Test
    void switchToFieldFour() {
        int actualResult = wheel.switchToField(4);
        assertEquals(50, actualResult);
    }

    @Test
    void switchToFieldFive() {
        int actualResult = wheel.switchToField(5);
        assertEquals(100, actualResult);
    }

    @Test
    void switchToFieldSix() {
        int actualResult = wheel.switchToField(6);
        assertEquals(200, actualResult);
    }

    @Test
    void spinTheWheelInBoundaries() {
        int actualResult = wheel.spinTheWheel();
        assertTrue(actualResult >= 1 && actualResult <= 7);
    }

//    /**
//     * Function create artificial sleeper, then pass it to Wheel constructor, invoke a method that should trigger sleep(). Finally check if sleep() has been invoked only once.
//     */
//    @Test
//    void spinShouldCallSleep() {
//        Sleeper mockSleeper = mock(Sleeper.class);
//        Wheel wheel = new Wheel(mockSleeper);
//        wheel.spinTheWheel();
//        verify(mockSleeper, times(1)).sleep();
//    }
}