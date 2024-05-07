package university.jala.usersapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User.UserBuilder;
import university.jala.usersapi.core.domain.models.dto.response.UserDTO;
import university.jala.usersapi.core.domain.models.dto.response.UserDTOById;
import university.jala.usersapi.core.application.service.UserService;
import university.jala.usersapi.api.controller.UserController;
import university.jala.usersapi.core.domain.models.entities.User;

public class UpdateUserTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateOneUserField() throws Exception {
        // Arrange
        String userId = "user_id";
        UserDTOById updatedUser = UserDTOById.builder().name("New Name")
                .build();

        User user = User.builder()
            .id(userId)
            .name(updatedUser.getName())
            .login("random")
            .password("random")
            .build();

        // Mock
        when(
            userService.updateByID(any(UserDTOById.class), eq(userId))).thenReturn(
            user);

        // Act
        ResponseEntity<UserDTO> response = (ResponseEntity<UserDTO>)
            userController.updateUserById(updatedUser, userId);
        UserDTO result = response.getBody();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser.getName(), result.getName());

        // Verify
        verify(userService, times(1)).updateByID(any(UserDTOById.class),
                eq(userId));
    }

    @Test
    void testUpdateAllUserFields() throws Exception {
        // Arrange
        String userId = "user_id";
        UserDTOById updatedUser = UserDTOById.builder()
                .name("New Name")
                .login("newlogin")
                .password("newpassword")
                .build();

        User user = User.builder()
            .id(userId)
            .name(updatedUser.getName())
            .login(updatedUser.getLogin())
            .password(updatedUser.getPassword())
            .build();

        // Mock
        when(
                userService.updateByID(any(UserDTOById.class), eq(userId))).thenReturn(
            user);

        // Act
        ResponseEntity<UserDTO> response = (ResponseEntity<UserDTO>)
                userController.updateUserById(updatedUser, userId);
        UserDTO result = response.getBody();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser.getName(), result.getName());
        assertEquals(updatedUser.getLogin(), result.getLogin());
        assertEquals(updatedUser.getPassword(), result.getPassword());

        // Verify
        verify(userService, times(1)).updateByID(any(UserDTOById.class),
                eq(userId));
    }
}
