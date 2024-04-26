package university.jala.usersapi;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import university.jala.usersapi.core.domain.models.entities.User;
import university.jala.usersapi.core.application.service.AuthService;
import university.jala.usersapi.core.domain.models.dto.response.AuthenticationResponseDTO;
import university.jala.usersapi.core.domain.models.dto.request.RegisterRequestDTO;
import university.jala.usersapi.api.controller.AuthController;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class CreateUserTest {

  @Mock
  private AuthService authService;

  @InjectMocks
  private AuthController authController;

  private Validator validator;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  /**
   * Test to simulate the creation of a new user.
   */

  @Test
  public void testCreateUser() throws Exception {

    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setName("TestUser");
    registerRequestDTO.setLogin("test-user");
    registerRequestDTO.setPassword("12345");

    AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
    String tokenAuth = authenticationResponseDTO.getToken();
    authenticationResponseDTO.setToken(tokenAuth);

    when(authService.register(registerRequestDTO)).thenReturn((authenticationResponseDTO));

    ResponseEntity<?> responseEntity = authController.userRegister(registerRequestDTO);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
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
