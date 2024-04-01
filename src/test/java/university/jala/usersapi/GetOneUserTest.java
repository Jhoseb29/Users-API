package university.jala.usersapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
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

public class GetOneUserTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Test to confirm the return of an existent user.
   */
  @Test
  public void testGetUserById_ExistingUser() {

    String userId = UUID.randomUUID().toString();
    User mockUser = new User();
    mockUser.setId(userId);
    mockUser.setName("Test User");
    mockUser.setLogin("testuser");
    mockUser.setPassword("password");

    // Mock del servicio para devolver un Optional con el usuario simulado
    when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));

    // Llamada al método del controlador para obtener la respuesta
    ResponseEntity<?> response = userController.getUserById(userId);

    // Verificación del código de estado de la respuesta
    assertEquals(HttpStatus.OK, response.getStatusCode());

    // Obtener el cuerpo de la respuesta
    Optional<?> responseBody = (Optional<?>) response.getBody();

    // Verificar que el Optional no esté vacío
    assertTrue(responseBody.isPresent());

    // Obtener el usuario del Optional
    User userResponse = (User) responseBody.get();

    // Verificar los atributos del usuario devuelto
    assertEquals(mockUser.getId(), userResponse.getId());
    assertEquals(mockUser.getName(), userResponse.getName());
    assertEquals(mockUser.getLogin(), userResponse.getLogin());
    assertEquals(mockUser.getPassword(), userResponse.getPassword());

    // Verificación de la llamada al método del servicio
    verify(userService, times(1)).getUserById(userId);
  }
  /**
   * Test to confirm the handler of a non-existent user.
   */
  @Test
  public void testGetUserById_NonExistingUser() {

    String userId = UUID.randomUUID().toString();
    when(userService.getUserById(userId)).thenReturn(Optional.empty());

    ResponseEntity<?> response = userController.getUserById(userId);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    verify(userService, times(1)).getUserById(userId);
  }
}