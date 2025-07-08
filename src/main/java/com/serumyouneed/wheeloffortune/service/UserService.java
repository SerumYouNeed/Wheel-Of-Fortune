package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.dao.UserDao;
import com.serumyouneed.wheeloffortune.model.User;
import com.serumyouneed.wheeloffortune.utils.InputUtils;
import com.serumyouneed.wheeloffortune.utils.Messages;
import com.serumyouneed.wheeloffortune.utils.Printer;
import org.mindrot.jbcrypt.BCrypt;

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

    /**
     * Entry User engine. Depending on input create new User in database, log old one or allow to play as guest.
     * @return user (User)
     */
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
        String nickname;
        do {
            nickname = promptNickname();
        } while (!UserDao.checkingNicknameUniqueness(nickname));
        String plainPassword = promptPassword();
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        return UserDao.setNewUserToDatabase(nickname, hashedPassword);
    }

    public User createGuestUser() {
        String nickname = promptNickname();
        return new User(nickname, true);
    }

    public User loginUser() {
        int loggingChances = 3;
        String nickname = promptNickname();
        String plainPassword = promptPassword();
        String hashedPasswordFromDatabase = UserDao.getHashedPasswordFromDatabase(nickname);
        if (BCrypt.checkpw(plainPassword, hashedPasswordFromDatabase)) {
            Printer.print(Messages.HELLO_AFTER_LOGGING + nickname);
            return new User(nickname, hashedPasswordFromDatabase, false);
        } else {
            Printer.print(Messages.ERROR_PASSWORD);
        }
        return UserDao.searchUserInDatabase(nickname);
    }

    /**
     * Prompt user for nickname, trim input and return string in uppercase format
     * @return nickname (String)
     */
    private String promptNickname() {
        Printer.print(Messages.ENTER_NICKNAME);
        return readUppercaseInput(scanner);
    }
    private String promptPassword() {
        Printer.print(Messages.ENTER_PASSWORD);
        return scanner.nextLine();
    }

    /**
     * Prompt user if one want to play as guest, log in or create new account.
     * @return user choice (OptionalInt)
     */
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
