package university.jala.usersapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllUsersTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllUsers() {

        String userId = UUID.randomUUID().toString();
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(userId);
        user1.setName("User1");
        user1.setLogin("user1");
        user1.setPassword("pass1");
        userList.add(user1);

        // Mocking the behavior of userService.getAllUsers(page, size)
        when(userService.getAllUsers(anyInt(), anyInt())).thenReturn(userList);

        // Calling the method in UserController
        List<User> users = userController.getAllUsers(0, 10).getBody(); // Assuming 0 for page and 10 for size

        // Assertions
        assertEquals(1, users.size());
        assertEquals("User1", users.get(0).getName());
        assertEquals("user1", users.get(0).getLogin());
        assertEquals("pass1", users.get(0).getPassword());

        // Verifying the method call
        verify(userService, times(1)).getAllUsers(anyInt(), anyInt());
    }

    @Test
    public void testGetAllUsersEmptyList() {
        // Mocking the behavior of userService.getAllUsers(page, size) to return an empty list
        when(userService.getAllUsers(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        // Calling the method in UserController
        List<User> users = userController.getAllUsers(0, 10).getBody();

        // Assertions for an empty list
        assertEquals(0, users.size());
    }

    @Test
    public void testGetAllUsersMultipleUsers() {
        // Creating multiple users for testing
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName("User" + i);
            user.setLogin("user" + i);
            user.setPassword("pass" + i);
            userList.add(user);
        }

        // Mocking the behavior of userService.getAllUsers(page, size) to return multiple users
        when(userService.getAllUsers(anyInt(), anyInt())).thenReturn(userList);

        // Calling the method in UserController
        List<User> users = userController.getAllUsers(0, 10).getBody();

        // Assertions for multiple users
        assertEquals(5, users.size());
    }
}