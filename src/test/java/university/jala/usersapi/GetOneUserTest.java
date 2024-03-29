package university.jala.usersapi;

import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;
import university.jala.usersapi.presentation.controller.UserController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetOneUserTest{

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetUserById_ExistingUser() {

    String userId = UUID.randomUUID().toString();
    User mockUser = new User();
    mockUser.setId(userId);
    mockUser.setName("Test User");
    mockUser.setLogin("testuser");
    mockUser.setPassword("password");

    when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));

    ResponseEntity<User> response = userController.getUserById(userId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(mockUser, response.getBody());
    verify(userService, times(1)).getUserById(userId);
  }

  @Test
  public void testGetUserById_NonExistingUser() {

    String userId = UUID.randomUUID().toString();
    when(userService.getUserById(userId)).thenReturn(Optional.empty());

    ResponseEntity<User> response = userController.getUserById(userId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(userService, times(1)).getUserById(userId);
  }
}