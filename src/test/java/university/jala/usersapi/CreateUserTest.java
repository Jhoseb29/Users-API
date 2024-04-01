package university.jala.usersapi;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.AuthService;
import university.jala.usersapi.domain.service.security.dto.AuthenticationResponseDTO;
import university.jala.usersapi.domain.service.security.dto.RegisterRequestDTO;
import university.jala.usersapi.presentation.controller.AuthController;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class CreateUserTest {

  @Mock
  private AuthService authService;

  @InjectMocks
  private AuthController authController;

  private Validator validator;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  /**
   * Test to simulate the creation of a new user.
   */

  @Test
  public void testCreateUser() {

    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setName("TestUser");
    registerRequestDTO.setLogin("test-user");
    registerRequestDTO.setPassword("12345");

    AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
    String tokenAuth = authenticationResponseDTO.getToken();
    authenticationResponseDTO.setToken(tokenAuth);

    when(authService.register(registerRequestDTO)).thenReturn((authenticationResponseDTO));

    ResponseEntity<?> responseEntity = authController.userRegister(registerRequestDTO);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(authenticationResponseDTO, responseEntity.getBody());
    verify(authService, times(1)).register(registerRequestDTO);
  }

  /**
   * Test to simulate the creation of a new user with invalid data.
   */
  @Test
  public void createUserWithInvalidFields() {
    User user = User.builder()
        .name("John Doe")
        .login("johndoe123")
        .password("")
        .build();

    Set<ConstraintViolation<User>> violations = validator.validate(user);

    assertFalse(violations.isEmpty());
    assertEquals(1, violations.size());

    ConstraintViolation<User> violation = violations.iterator().next();
    assertEquals("The field 'password' cannot be empty.", violation.getMessage());
  }
}
