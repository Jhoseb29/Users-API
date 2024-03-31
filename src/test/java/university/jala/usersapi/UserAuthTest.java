package university.jala.usersapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;

public class UserAuthTest {
  @Mock
  private UserService userService;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Testing password encryption using Spring Security.
   */
  @Test
  public void testPasswordEncryption() {
    String plainPassword = "password123";
    String encodedPassword = "fpadfpai0-2i3934u";

    User user = new User();
    user.setName("myNameIs");
    user.setLogin("newUser");
    user.setPassword(plainPassword);

    when(passwordEncoder.encode(plainPassword)).thenReturn(encodedPassword);
    userController.registerUser(user);

    verify(passwordEncoder, times(1)).encode(plainPassword);
    assertEquals(userService.getUserById(user.getId()).get().getPassword(), encodedPassword);
  }
}
