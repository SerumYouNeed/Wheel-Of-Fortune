package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.dao.UserDao;
import com.serumyouneed.wheeloffortune.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateNewUser_whenNicknameIsUnique_shouldReturnNewUser() {
        String nickname = "JOHNDOE";
        String password = "password123";

        when(scanner.nextLine()).thenReturn(nickname, password);

        try (MockedStatic<UserDao> mockedStatic = mockStatic(UserDao.class)) {
            mockedStatic.when(() -> UserDao.checkingNicknameUniqueness(nickname)).thenReturn(true);

            mockedStatic.when(() -> UserDao.setNewUserToDatabase(eq(nickname), anyString()))
                    .thenReturn(new User(nickname, BCrypt.hashpw(password, BCrypt.gensalt()), false));

            User result = userService.createNewUser();

            assertNotNull(result);
            assertEquals(nickname, result.getNickname());
            assertFalse(result.isGuest());
        }
    }

    @Test
    void testCreateGuestUser() {
        String nickname = "JOHNDOE";

        when(scanner.nextLine()).thenReturn(nickname);

        User result = userService.createGuestUser();

        assertNotNull(result);
        assertEquals(nickname, result.getNickname());
        assertTrue(result.isGuest());
    }

    @Test
    void testLoggingUser() {
        String nickname = "JOHNDOE";
        String password = "password123";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        when(scanner.nextLine()).thenReturn(nickname, password);

        try (MockedStatic<UserDao> mockedStatic = mockStatic(UserDao.class)) {
            mockedStatic.when(() -> UserDao.searchUserInDatabase(nickname))
                    .thenReturn(new User(nickname, hashedPassword, false));

            User result = userService.loginUser();

            assertNotNull(result);
            assertEquals(nickname, result.getNickname());
            assertFalse(result.isGuest());
        }
    }

    @Test
    void testLoggingUser_afterThreeTimesWrongPassword() {
        String nickname = "JOHNDOE";
        String correctPassword = "password123";
        String wrongPassword = "123password";

        when(scanner.nextLine()).thenReturn(nickname, wrongPassword, wrongPassword, wrongPassword);

        String hashedPassword = BCrypt.hashpw(correctPassword, BCrypt.gensalt());
        User userFromDatabase = new User(nickname, hashedPassword, false);

        try (MockedStatic<UserDao> mockedStatic = mockStatic(UserDao.class)) {
            mockedStatic.when(() -> UserDao.searchUserInDatabase(nickname))
                    .thenReturn(userFromDatabase);

            User result = userService.loginUser();

            assertNull(result);
        }
    }
}