package net.simplecrud.springbootdemo.testcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.simplecrud.springbootdemo.controller.UserController;
import net.simplecrud.springbootdemo.model.User;
import net.simplecrud.springbootdemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "User 1"));
        users.add(new User(2L, "User 2"));

        when(userService.findAll()).thenReturn(users);

        String viewName = userController.findALl(model);

        assertEquals("user-list", viewName);
        verify(model).addAttribute("users", users);
    }

    @Test
    public void testCreateUser() {
        User newUser = new User(1L,"John Doe");

        String viewName = userController.createUser(newUser);

        assertEquals("redirect:/users", viewName);
        verify(userService).saveUser(newUser);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        String viewName = userController.deleteUser(userId);

        assertEquals("redirect:/users", viewName);
        verify(userService).deleteById(userId);
    }

    @Test
    public void testUpdateUserForm() {
        Long userId = 1L;
        User user = new User(userId, "John Doe");

        when(userService.findById(userId)).thenReturn(Optional.of(user));

        String viewName = userController.updateUserForm(userId, model);

        assertEquals("user-update", viewName);
        verify(model).addAttribute("user", Optional.of(user));
    }

    @Test
    public void testUpdateUser() {
        User userToUpdate = new User(1L,"Updated User");

        String viewName = userController.updateUser(userToUpdate);

        assertEquals("redirect:/users", viewName);
        verify(userService).saveUser(userToUpdate);
    }
}