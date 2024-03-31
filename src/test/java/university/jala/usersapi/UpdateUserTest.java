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
import university.jala.usersapi.domain.models.User;
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
    User updatedUser = new User();
    updatedUser.setId(userId);
    updatedUser.setName("New Name");

    // Mock
    when(userService.updateByID(any(User.class), eq(userId))).thenReturn(
        updatedUser);

    // Act
    ResponseEntity<User> response = (ResponseEntity<User>) userController.updateUserById(
        updatedUser,
        userId);
    User result = response.getBody();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedUser.getName(), result.getName());

    // Verify
    verify(userService, times(1)).updateByID(any(User.class), eq(userId));
  }

  @Test
  void testUpdateAllUserFields() {
    // Arrange
    String userId = "user_id";
    User updatedUser = new User();
    updatedUser.setId(userId);
    updatedUser.setName("New Name");
    updatedUser.setLogin("newlogin");
    updatedUser.setPassword("newpassword");

    // Mock
    when(userService.updateByID(any(User.class), eq(userId))).thenReturn(
        updatedUser);

    // Act
    ResponseEntity<User> response = (ResponseEntity<User>) userController.updateUserById(
        updatedUser,
        userId);
    User result = response.getBody();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedUser.getName(), result.getName());
    assertEquals(updatedUser.getLogin(), result.getLogin());
    assertEquals(updatedUser.getPassword(), result.getPassword());

    // Verify
    verify(userService, times(1)).updateByID(any(User.class), eq(userId));
  }
}
