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

        when(userService.getAllUsers()).thenReturn(userList);

        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("User1", users.get(0).getName());
        assertEquals("user1", users.get(0).getLogin());
        assertEquals("pass1", users.get(0).getPassword());


        verify(userService, times(1)).getAllUsers();
    }
}