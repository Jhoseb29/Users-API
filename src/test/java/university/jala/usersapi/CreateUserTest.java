package university.jala.usersapi;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateUserTest {
@Mock
    private UserService userService;

@InjectMocks
    private UserController userController;

@BeforeEach
    public void setUp(){
    MockitoAnnotations.openMocks(this);
}

    /**
     * Test to simulate the creation of a new user.
     */

@Test
public void testCreate_User(){
    String userId = UUID.randomUUID().toString();
    User newUser = new User();
    newUser.setId(userId);
    newUser.setName("UserTest01");
    newUser.setLogin("usertest01");
    newUser.setPassword("12345");

    UserService userService = mock(UserService.class);
    when (userService.createUser(any(User.class))).thenReturn(newUser);

    UserController userController = new UserController();

    ResponseEntity<User> response = userController.createUser(newUser);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(newUser, response.getBody());
    verify(userService, times(1)).createUser(newUser);
}
}
