package university.jala.usersapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import university.jala.usersapi.domain.service.AuthService;
import university.jala.usersapi.domain.service.JwtService;
import university.jala.usersapi.domain.models.dto.AuthenticationResponseDTO;
import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;
import university.jala.usersapi.domain.service.exception.WrongDataException;
import university.jala.usersapi.domain.util.RequestDataValidatorUtil;
import university.jala.usersapi.persistance.repository.UserRepository;


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
    when(jwtService.getToken(any())).thenReturn(jwtToken);

    AuthenticationResponseDTO responseDTO = authService.register(registerRequestDTO);

    Assertions.assertNotNull(responseDTO);
    Assertions.assertEquals(jwtToken, responseDTO.getToken());

    verify(userRepository, times(1)).save(any());
  }
}
