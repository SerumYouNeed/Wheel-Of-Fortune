package com.serumyouneed.wheeloffortune.service;

import com.serumyouneed.wheeloffortune.dao.UserDao;
import com.serumyouneed.wheeloffortune.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

        mockStatic(UserDao.class);
        when(UserDao.checkingNicknameUniqueness(nickname)).thenReturn(true);

        when(UserDao.setNewUserToDatabase(eq(nickname), anyString()))
                .thenReturn(new User(nickname, BCrypt.hashpw(password, BCrypt.gensalt()), false));

        User result = userService.createNewUser();

        assertNotNull(result);
        assertEquals(nickname, result.getNickname());
        assertFalse(result.isGuest());
        assertTrue(BCrypt.checkpw(password, result.getHashedPassword()));
    }
}
