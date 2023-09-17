package net.simplecrud.springbootdemo.testservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.simplecrud.springbootdemo.model.User;
import net.simplecrud.springbootdemo.repository.UserRepository;
import net.simplecrud.springbootdemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Long userId = 1L;
        User expectedUser = new User(userId, "John Doe");

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        Optional<User> foundUser = userService.findById(userId);

        assertTrue(foundUser.isPresent());
        assertEquals(expectedUser, foundUser.get());
    }

    @Test
    public void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "User 1"));
        users.add(new User(2L, "User 2"));

        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = userService.findAll();

        assertEquals(users.size(), foundUsers.size());
        assertEquals(users, foundUsers);
    }

    @Test
    public void testSaveUser() {
        User newUser = new User(1L,"John Doe");
        User savedUser = new User(1L, "John Doe");

        when(userRepository.save(newUser)).thenReturn(savedUser);

        User returnedUser = userService.saveUser(newUser);

        assertEquals(savedUser, returnedUser);
    }

    @Test
    public void testDeleteById() {
        Long userId = 1L;

        userService.deleteById(userId);

        verify(userRepository).deleteById(userId);
    }
}
