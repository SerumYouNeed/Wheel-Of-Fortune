package com.serumyouneed.wheeloffortune.model;

/**
 * Class for logging purpose. Help to create player profile in database.
 */
public class User {
    private int id;
    private boolean isGuest;
    private String nickname;

    public User (String nickname, boolean isGuest) {
        this.nickname = nickname;
        this.isGuest = isGuest;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isGuest() {
        return isGuest;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

}
