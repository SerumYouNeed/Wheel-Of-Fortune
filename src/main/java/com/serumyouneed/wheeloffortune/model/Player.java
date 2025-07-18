package com.serumyouneed.wheeloffortune.model;

/**
 * Player class simulates money flow on player's account.
 */
public class Player {


    private final User user;
    private int money;

    public Player(int startingMoney, User user) {
        this.user = user;
        this.money = startingMoney;
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

    /**
     * Function increase player's money balance
     *
     * @param amount (int): added value to account
     */
    public void addMoney(int amount) {
        this.money += amount;
    }

    /**
     * Function decrease player's money balance
     * @param amount (int): decreased value to account
     */
    public void subtractMoney(int amount) {
        this.money -= amount;
    }
}