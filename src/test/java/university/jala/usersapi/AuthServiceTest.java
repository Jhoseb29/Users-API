package university.jala.usersapi;


import org.junit.Test;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.models.dto.AuthenticationResponseDTO;
import university.jala.usersapi.domain.service.AuthService;
import university.jala.usersapi.domain.service.JwtService;
import university.jala.usersapi.domain.service.exception.UserNotFoundException;
import university.jala.usersapi.domain.util.DataValidator;
import university.jala.usersapi.persistance.repository.UserRepository;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthServiceTest {



    // Authenticate user with valid credentials and return JWT token
    @Test
    public void test_authenticate_user_with_valid_credentials() throws Exception {
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
        when(jwtService.getToken(user)).thenReturn("jwtToken");

        // Act
        AuthenticationResponseDTO response = authService.login(authenticationRequest);

        // Assert
        assertEquals("jwtToken", response.getToken());
    }

    // Authenticate user with non-existent login and return failure
    @Test
    public void test_authenticate_user_with_nonexistent_login() {
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
    public void test_throw_user_not_found_exception_if_user_not_found(){
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
    public void test_throw_wrong_data_exception_if_fields_empty_or_null() throws Exception {

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