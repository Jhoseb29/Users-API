package university.jala.usersapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import university.jala.usersapi.domain.service.JwtService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JwtServiceTest {

    // generate JWT token with valid user details
    @Test
    public void testGenerateJwtTokenWithValidUserDetails() {
        // Arrange
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");
        JwtService jwtService = new JwtService();

        // Act
        String token = jwtService.getToken(userDetails);

        // Assert
        Assertions.assertNotNull(token);
    }

    // retrieve login username from valid JWT token
    @Test
    public void testRetrieveLoginUsernameFromValidJwtToken() {
        // Arrange
        String expectedLogin = "testUser";
        String token = "validJwtToken";
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.getLoginFromToken(token)).thenReturn(expectedLogin);

        // Act
        String login = jwtService.getLoginFromToken(token);

        // Assert
        Assertions.assertEquals(expectedLogin, login);
    }

    // generate JWT token with null user details
    @Test
    public void testGenerateJwtTokenWithNullUserDetails() {
        // Arrange
        UserDetails userDetails = null;
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.getToken(userDetails)).thenThrow(new NullPointerException());

        // Act & Assert
        assertThrows(NullPointerException.class, () -> jwtService.getToken(userDetails));
    }

    // Should return null if provided with a null JWT token
    @Test
    public void testRetrieveLoginUsernameFromNullJwtToken() {
        // Arrange
        JwtService jwtService = mock(JwtService.class);
        String token = null;
        when(jwtService.getLoginFromToken(token)).thenThrow(new IllegalArgumentException("Token cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> jwtService.getLoginFromToken(token));
    }

    // validate null JWT token for given user details
    @Test
    public void testValidateNullJwtTokenForGivenUserDetails() {
        // Arrange
        UserDetails userDetails = mock(UserDetails.class);
        JwtService jwtService = mock(JwtService.class);
        String token = null;
        when(jwtService.isTokenValid(token, userDetails)).thenThrow(new IllegalArgumentException("Token cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> jwtService.isTokenValid(token, userDetails));
    }

    @Test
    public void testRetrieveLoginUsernameFromValidJwtToken2() {
        // Arrange
        String expectedLogin = "testUser";
        String token = "validJwtToken";
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.getLoginFromToken(token)).thenReturn(expectedLogin);

        // Act
        String login = jwtService.getLoginFromToken(token);

        // Assert
        Assertions.assertEquals(expectedLogin, login);
    }
}
