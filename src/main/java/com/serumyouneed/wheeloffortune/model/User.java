package com.serumyouneed.wheeloffortune.model;

/**
 * Class for logging purpose. Help to create player profile in database.
 */
public class User {
    private final boolean isGuest;
    private final String nickname;
    private String hashedPassword;

    public User (String nickname, String hashedPassword, boolean isGuest) {
        this.nickname = nickname;
        this.isGuest = isGuest;
        this.hashedPassword = hashedPassword;
    }
    public User (String nickname, boolean isGuest) {
        this.nickname = nickname;
        this.isGuest = isGuest;
    }

    // Getters
    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getNickname() {
        return nickname;
    }
}
