package net.simplecrud.springbootdemo.testcontroller;

import jakarta.transaction.Transactional;
import net.simplecrud.springbootdemo.model.User;
import net.simplecrud.springbootdemo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@DirtiesContext
public class UserControllerDatabaseTest {

    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setUp() {
        // Clear the database before each test
        userRepository.deleteAll();
    }
    @Test
    public void testCrudOperations() {
        // Create a user
        User user = new User();
        user.setFirstName("Rayka");
        user.setLastName("GGG");
        userRepository.save(user);

        // Read operation
        List<User> savedUsers = userRepository.findByFirstName("Rayka");
        assertNotNull(savedUsers);
        assertEquals(1, savedUsers.size());
        User savedUser = savedUsers.get(0);
        assertEquals("Rayka", savedUser.getFirstName());
        assertEquals("GGG", savedUser.getLastName());

        // Update operation
        savedUser.setFirstName("UpdatedName");
        savedUser.setLastName("UpdatedLastName");
        userRepository.save(savedUser);

        // Read the updated user
        List<User> updatedUsers = userRepository.findByFirstName("UpdatedName");
        assertNotNull(updatedUsers);
        assertEquals(1, updatedUsers.size());
        User updatedUser = updatedUsers.get(0);
        assertEquals("UpdatedName", updatedUser.getFirstName());
        assertEquals("UpdatedLastName", updatedUser.getLastName());

        // Delete operation
        userRepository.deleteById(updatedUser.getId());

        // Verify the user was deleted
        List<User> deletedUsers = userRepository.findByFirstName("UpdatedName");
        assertEquals(0, deletedUsers.size());
    }
}
