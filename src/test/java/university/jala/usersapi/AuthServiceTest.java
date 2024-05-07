package university.jala.usersapi;


import org.junit.jupiter.api.Test;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import university.jala.usersapi.core.domain.models.entities.User;
import university.jala.usersapi.core.domain.models.dto.request.AuthenticationRequestDTO;
import university.jala.usersapi.core.domain.models.dto.response.AuthenticationResponseDTO;
import university.jala.usersapi.core.application.service.AuthService;
import university.jala.usersapi.core.application.security.jwt.JwtService;
import university.jala.usersapi.core.domain.exceptions.UserNotFoundException;
import university.jala.usersapi.core.application.utils.DataValidator;
import university.jala.usersapi.data.mongodb.repository.UserRepository;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    // Authenticate user with valid credentials and return JWT token
    @Test
    public void testAuthenticateUserWithValidCredentials() throws Exception {
        /* Arrange */

        UserRepository userRepository = mock(UserRepository.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        DataValidator dataValidator = mock(DataValidator.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthService authService = new AuthService(userRepository, jwtService, passwordEncoder, authenticationManager, dataValidator);

        AuthenticationRequestDTO authenticationRequest = new AuthenticationRequestDTO();
        authenticationRequest.setLogin("testUser");
        authenticationRequest.setPassword("testPassword");

        User user = new User();
        user.setLogin("testUser");
        user.setPassword("encodedPassword");

        when(userRepository.findByLogin(authenticationRequest.getLogin())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtService.getToken(user, user.get_id())).thenReturn("jwtToken");

        // Act
        AuthenticationResponseDTO response = authService.login(authenticationRequest);

        // Assert
        assertEquals("jwtToken", response.getToken());
    }

    // Authenticate user with non-existent login and return failure
    @Test
    public void testAuthenticateUserWithNonexistentLogin() {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        DataValidator dataValidator = mock(DataValidator.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);

        AuthService authService = new AuthService(userRepository, jwtService, passwordEncoder, authenticationManager, dataValidator);

        AuthenticationRequestDTO authenticationRequest = new AuthenticationRequestDTO();
        authenticationRequest.setLogin("nonexistentUser");
        authenticationRequest.setPassword("testPassword");

        when(userRepository.findByLogin(authenticationRequest.getLogin())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> authService.login(authenticationRequest));
    }

    @Test
    public void testThrowUserNotFoundExceptionIfUserNotFound() {
        UserRepository userRepository = mock(UserRepository.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        DataValidator dataValidator = mock(DataValidator.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthService authService = new AuthService(userRepository, jwtService, passwordEncoder, authenticationManager, dataValidator);
        // Arrange
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
        when(userRepository.findByLogin(requestDTO.getLogin())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> authService.login(requestDTO));
        verify(userRepository, times(1)).findByLogin(requestDTO.getLogin());
    }

    // Throw WrongDataException if login or password fields are empty or null
    @Test
    public void testThrowWrongDataExceptionIfFieldsEmptyOrNull() throws Exception {

        UserRepository userRepository = mock(UserRepository.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        DataValidator dataValidator = mock(DataValidator.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        AuthService authService = new AuthService(userRepository, jwtService, passwordEncoder, authenticationManager, dataValidator);
        // Arrange
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO(null, "password");

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> authService.login(requestDTO));
        verify(dataValidator, never()).validate("anyString", "anyString");
        verify(userRepository, never()).findByLogin(anyString());
        verify(authenticationManager, never()).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}