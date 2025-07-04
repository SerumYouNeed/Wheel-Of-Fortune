package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.dao.UserDao;
import com.serumyouneed.wheeloffortune.model.User;
import com.serumyouneed.wheeloffortune.utils.InputUtils;
import com.serumyouneed.wheeloffortune.utils.Messages;
import com.serumyouneed.wheeloffortune.utils.Printer;

import java.util.OptionalInt;
import java.util.Scanner;

import static com.serumyouneed.wheeloffortune.utils.InputUtils.readUppercaseInput;

/**
 * Utility class for creating, logging and checking User in database.
 */
public class UserService {
    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }
    public User startUser() {
        Printer.print(Messages.HELLO);
        User user = null;
        while (user == null) {
            OptionalInt choiceOpt = promptUserChoice();
            if (choiceOpt.isPresent()) {
                int choice = choiceOpt.getAsInt();
                switch (choice) {
                    case 1 -> user = createGuestUser();
                    case 2 -> user = createNewUser();
                    case 3 -> user = loginUser();
                    default -> Printer.print(Messages.INVALID_OPTION);
                }
            }
        }
        return user;
    }

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

    private OptionalInt promptUserChoice() {
        Printer.print(Messages.LOGGING_OPTIONS);
        if (!scanner.hasNextInt()) {
            Printer.print(Messages.ENTER_VALID_NUMBER);
            scanner.next();
            return OptionalInt.empty();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return OptionalInt.of(choice);
    }
}
