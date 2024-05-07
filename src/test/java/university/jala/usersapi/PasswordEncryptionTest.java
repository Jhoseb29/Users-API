package university.jala.usersapi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import university.jala.usersapi.core.application.service.AuthService;
import university.jala.usersapi.core.application.security.jwt.JwtService;
import university.jala.usersapi.core.domain.models.dto.response.AuthenticationResponseDTO;
import university.jala.usersapi.core.domain.models.dto.request.RegisterRequestDTO;
import university.jala.usersapi.core.application.utils.RequestDataValidatorUtil;
import university.jala.usersapi.data.mongodb.repository.UserRepository;


public class PasswordEncryptionTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private JwtService jwtService;

  @Mock
  private PasswordEncoder passwordEncoder;

  private final AuthService authService;

  public PasswordEncryptionTest() {
    MockitoAnnotations.openMocks(this);
    this.authService = new AuthService(userRepository, jwtService, passwordEncoder, null,
        new RequestDataValidatorUtil());
  }

  @Test
  public void testPasswordEncryption() throws Exception {
    RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
    registerRequestDTO.setName("John Doe");
    registerRequestDTO.setLogin("johndoe@example.com");
    registerRequestDTO.setPassword("password");

    String encodedPassword = "encodedPassword";
    when(passwordEncoder.encode(registerRequestDTO.getPassword())).thenReturn(encodedPassword);

    String jwtToken = "jwtToken";
    when(jwtService.getToken(any(), any())).thenReturn(jwtToken);

    AuthenticationResponseDTO responseDTO = authService.register(registerRequestDTO);

    Assertions.assertNotNull(responseDTO);
    Assertions.assertEquals(jwtToken, responseDTO.getToken());

    verify(userRepository, times(1)).save(any());
  }
}
