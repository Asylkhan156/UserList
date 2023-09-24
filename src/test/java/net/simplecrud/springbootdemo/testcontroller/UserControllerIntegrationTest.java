package net.simplecrud.springbootdemo.testcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    // You can add more dependencies like UserService and UserRepository if needed.

    @BeforeEach
    public void setUp() {
        // You can perform setup tasks here if necessary.
    }

    @Test
    public void testUserListPage() throws Exception {
        // Perform a GET request to the "/users" endpoint and check the response.
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUserCreatePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-create"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-create"));
    }

    @Test
    public void testUserCreate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user-create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                )
                .andExpect(status().is3xxRedirection()) // Expect a redirect after successful user creation.
                .andExpect(redirectedUrl("/users"));

        // You can add assertions to check if the user was created in the database if needed.
    }

    @Test
    public void testUserUpdatePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-update/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("user-update"));
    }

    @Test
    public void testUserUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user-update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("firstName", "UpdatedFirstName")
                        .param("lastName", "UpdatedLastName")
                )
                .andExpect(status().is3xxRedirection()) // Expect a redirect after successful user update.
                .andExpect(redirectedUrl("/users"));

        // You can add assertions to check if the user was updated in the database if needed.
    }

    @Test
    public void testUserDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-delete/{id}", 1L))
                .andExpect(status().is3xxRedirection()) // Expect a redirect after successful user deletion.
                .andExpect(redirectedUrl("/users"));

        // You can add assertions to check if the user was deleted from the database if needed.
    }

}
