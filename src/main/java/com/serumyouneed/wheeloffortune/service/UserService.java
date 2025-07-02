package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.dao.UserDao;
import com.serumyouneed.wheeloffortune.model.User;
import com.serumyouneed.wheeloffortune.utils.InputUtils;
import com.serumyouneed.wheeloffortune.utils.Messages;
import com.serumyouneed.wheeloffortune.utils.Printer;

import java.util.Scanner;

import static com.serumyouneed.wheeloffortune.utils.InputUtils.readUppercaseInput;

/**
 * Utility class for creating, logging and checking User in database.
 */
public class UserService {
    private final Scanner scanner = new Scanner(System.in);

    public User createNewUser() {
        String nickname = promptNickname();
        return UserDao.setNewUserToDatabase(nickname);
    }

    public User createGuestUser() {
        String nickname = promptNickname();
        return new User(nickname, true);
    }

    public User loginUser() {
        String nickname = promptNickname();
        return UserDao.searchUserInDatabase(nickname);
    }

    private String promptNickname() {
        Printer.print(Messages.ENTER_NICKNAME);
        return readUppercaseInput(scanner);
    }
}
