package com.serumyouneed.wheeloffortune.model;

/**
 * Player class simulates money flow on player's account.
 */
public class Player {

    private final User user;
    private int money;

    public Player(User user) {
        this.user = user;
    }

    /**
     * Getter of user profile
     * @return (User) user: user profile from database
     */
    public User getUser() {
        return user;
    }
    /**
     * Getter of money value
     * @return (int) money: actual players balance
     */
    public int getMoney() {
        return money;
    }
    /**
     * Function sets account balance
     * @param money (int): new account balance
     */
    public void setMoney(int money) {
        this.money = money;
    }
}