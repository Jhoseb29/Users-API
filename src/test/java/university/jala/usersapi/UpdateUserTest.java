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
import university.jala.usersapi.domain.models.dto.UserDTOById;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;

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
  void testUpdateOneUserField() {
    // Arrange
    String userId = "user_id";
    UserDTOById updatedUser = UserDTOById.builder().id(userId).name("New Name")
        .build();

    // Mock
    when(
        userService.updateByID(any(UserDTOById.class), eq(userId))).thenReturn(
        updatedUser);

    // Act
    ResponseEntity<UserDTOById> response = (ResponseEntity<UserDTOById>) userController.updateUserById(
        updatedUser,
        userId);
    UserDTOById result = response.getBody();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedUser.getName(), result.getName());

    // Verify
    verify(userService, times(1)).updateByID(any(UserDTOById.class),
        eq(userId));
  }

  @Test
  void testUpdateAllUserFields() {
    // Arrange
    String userId = "user_id";
    UserDTOById updatedUser = UserDTOById.builder()
        .id(userId)
        .name("New Name")
        .login("newlogin")
        .password("newpassword")
        .build();

    // Mock
    when(
        userService.updateByID(any(UserDTOById.class), eq(userId))).thenReturn(
        updatedUser);

    // Act
    ResponseEntity<UserDTOById> response = (ResponseEntity<UserDTOById>) userController.updateUserById(
        updatedUser,
        userId);
    UserDTOById result = response.getBody();

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
