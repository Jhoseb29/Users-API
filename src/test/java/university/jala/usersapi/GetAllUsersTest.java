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
    public void testGetAllUsersEmptyList() {
        when(userService.getAllUsersDTO(anyInt(), anyInt())).thenReturn(new ArrayList<>());

        ResponseEntity<?> responseEntity = userController.getAllUsers(0, 10);
        String responseBody = (String) responseEntity.getBody();

        assertEquals("No users found", responseBody);
    }


}
