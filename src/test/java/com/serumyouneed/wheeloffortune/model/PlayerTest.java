package com.serumyouneed.wheeloffortune.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerTest {

    @Mock
    private User user;

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(1000, user);
    }

    @Test
    void addMoney() {
        player.addMoney(100);
        int actualResult;
        actualResult = player.getMoney();
        assertEquals(1100, actualResult);
    }

    @Test
    void subtractMoney() {
        player.subtractMoney(100);
        int actualResult;
        actualResult = player.getMoney();
        assertEquals(900, actualResult);
    }

    @Test
    void setMoney() {
        player.setMoney(20);
        int actualResult;
        actualResult = player.getMoney();
        assertEquals(20, actualResult);
    }
}