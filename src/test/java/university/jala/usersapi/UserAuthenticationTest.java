package university.jala.usersapi;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import university.jala.usersapi.core.application.service.AuthService;
import university.jala.usersapi.core.domain.models.dto.request.AuthenticationRequestDTO;
import university.jala.usersapi.core.domain.models.dto.response.AuthenticationResponseDTO;
import university.jala.usersapi.api.controller.AuthController;


public class UserAuthenticationTest {
  @Mock
  private AuthService authService;

  @InjectMocks
  private AuthController authController;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    objectMapper = new ObjectMapper();
  }

  @Test
  void testUserAuthenticationSuccess() throws Exception {
    AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
    AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO("token");
    when(authService.login(requestDTO)).thenReturn(responseDTO);

    ResponseEntity<?> responseEntity = authController.userAuthentication(requestDTO);

    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    Assertions.assertEquals(responseDTO, responseEntity.getBody());
    verify(authService, times(1)).login(requestDTO);
  }

  @Test
  void testUserAuthenticationFailure() throws Exception {
    AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
    String errorMessage = "Error message";
    when(authService.login(requestDTO)).thenThrow(new RuntimeException(errorMessage));

    ResponseEntity<?> responseEntity = authController.userAuthentication(requestDTO);

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    verify(authService, times(1)).login(requestDTO);
  }


}
