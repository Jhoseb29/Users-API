package university.jala.usersapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import university.jala.usersapi.domain.utils.JsonUtils;
import university.jala.usersapi.presentation.controller.UserController;

public class UserAuthenticationTest {
  private MockMvc mockMvc;

  @Mock
  private AuthenticationHandler authenticationHandler;

  @Mock
  private JwtUtil jwtUtil;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testLogin() throws Exception {
    String login = "username";
    String password = "password";
    String jwt = "generatedJwtToken";

    AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO();
    authenticationRequestDTO.setLogin(login);
    authenticationRequestDTO.setPassword(password);

    Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
    when(authenticationHandler.authenticate(authentication)).thenReturn(authentication);
    when(jwtUtil.create(login)).thenReturn(jwt);

    mockMvc.perform(post("/users/authentication")
            .contentType(MediaType.APPLICATION_JSON).content(JsonUtils.asJsonString(authenticationRequestDTO)))
        .andExpect(status().isOk());
  }


}
