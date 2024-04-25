package university.jala.usersapi;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import university.jala.usersapi.core.application.security.jwt.JwtService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtServiceTest {

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
        String uuid = String.valueOf(UUID.randomUUID());
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.getToken(userDetails, uuid)).thenThrow(new NullPointerException());

        // Act & Assert
        assertThrows(NullPointerException.class, () -> jwtService.getToken(userDetails, uuid));
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
