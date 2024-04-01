package university.jala.usersapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;
import university.jala.usersapi.domain.models.dto.UserDTO;

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
        List<UserDTO> userList = new ArrayList<>();
        UserDTO user1 = new UserDTO();
        user1.setId(userId);
        user1.setName("User1");
        user1.setLogin("user1");
        userList.add(user1);

        when(userService.getAllUsersDTO(anyInt(), anyInt())).thenReturn(userList);

        ResponseEntity<?> responseEntity = userController.getAllUsers(0, 10);
        List<UserDTO> users = (List<UserDTO>) responseEntity.getBody();

        assertEquals(1, users.size());
        assertEquals("User1", users.get(0).getName());
        assertEquals("user1", users.get(0).getLogin());

        verify(userService, times(1)).getAllUsersDTO(anyInt(), anyInt());
    }

    @Test
    public void testGetAllUsersEmptyList() {
        when(userService.getAllUsersDTO(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        ResponseEntity<?> responseEntity = userController.getAllUsers(0, 10);
        String responseBody = (String) responseEntity.getBody();

        assertEquals("No users found", responseBody);
    }

    @Test
    public void testGetAllUsersMultipleUsers() {
        List<UserDTO> userList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            UserDTO user = new UserDTO();
            user.setId(UUID.randomUUID().toString());
            user.setName("User" + i);
            user.setLogin("user" + i);
            userList.add(user);
        }

        when(userService.getAllUsersDTO(anyInt(), anyInt())).thenReturn(userList);

        ResponseEntity<?> responseEntity = userController.getAllUsers(0, 10);
        List<User> users = (List<User>) responseEntity.getBody();

        assertEquals(5, users.size());
    }
}
