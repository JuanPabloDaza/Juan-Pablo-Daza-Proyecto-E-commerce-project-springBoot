package com.jtspringproject.JtSpringProject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.jtspringproject.JtSpringProject.services.userService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    userService userService;

    @Mock
    userDao userDao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUsersTest() {
        User user1 = new User();
        User user2 = new User();
        when(userDao.getAllUser()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getUsers();

        assertEquals(2, users.size());
        verify(userDao, times(1)).getAllUser();
    }

    @Test
    public void addUserTest() {
        User user = new User();
        when(userDao.saveUser(user)).thenReturn(user);

        User createdUser = userService.addUser(user);

        assertEquals(user, createdUser);
        verify(userDao, times(1)).saveUser(user);
    }

    @Test
    public void checkLoginTest() {
        User user = new User();
        String username = "test";
        String password = "password";
        when(userDao.getUser(username, password)).thenReturn(user);

        User loggedUser = userService.checkLogin(username, password);

        assertEquals(user, loggedUser);
        verify(userDao, times(1)).getUser(username, password);
    }

    @Test
    public void checkUserExistsTest() {
        String username = "test";
        when(userDao.userExists(username)).thenReturn(true);

        boolean exists = userService.checkUserExists(username);

        assertEquals(true, exists);
        verify(userDao, times(1)).userExists(username);
    }
}