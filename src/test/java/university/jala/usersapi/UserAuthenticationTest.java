package university.jala.usersapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import university.jala.usersapi.domain.models.AuthenticationRequestDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import university.jala.usersapi.domain.utils.JwtUtils;
import university.jala.usersapi.presentation.controller.UserController;

public class UserAuthenticationTest {
  private MockMvc mockMvc;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtUtils jwtUtils;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testLogin() throws Exception {
// Mock data
    AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
    requestDTO.setLogin("testUser");
    requestDTO.setPassword("testPassword");

    UserDetails userDetails = mock(UserDetails.class);
    Authentication authentication = mock(Authentication.class);
    when(authentication.isAuthenticated()).thenReturn(true);
    when(authentication.getPrincipal()).thenReturn(userDetails);

    // Mock authenticationManager
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenReturn(authentication);

    // Mock jwtUtils
    when(jwtUtils.createToken("testUser")).thenReturn("mockedJWTToken");

    // Call the controller method
    ResponseEntity<?> responseEntity = userController.userAuthentication(requestDTO);

    // Verify the response
    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }


}
