package university.jala.usersapi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import university.jala.usersapi.domain.service.JwtService;


import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JwtServiceTest {


    // generate JWT token with valid user details
    @Test
    public void test_generate_jwt_token_with_valid_user_details() {
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
    public void test_retrieve_login_username_from_valid_jwt_token() {
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


    // retrieve all claims from valid JWT token
    @Test
    public void test_retrieve_all_claims_from_valid_jwt_token() {
        // Arrange
        String token = "validJwtToken";
        Claims expectedClaims = Jwts.claims();
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.getAllClaims(token)).thenReturn(expectedClaims);

        // Act
        Claims claims = jwtService.getAllClaims(token);

        // Assert
        Assertions.assertNotNull(claims);
        Assertions.assertEquals(expectedClaims, claims);
    }

    // generate JWT token with null user details
    @Test
    public void test_generate_jwt_token_with_null_user_details() {
        // Arrange
        UserDetails userDetails = null;
        JwtService jwtService = mock(JwtService.class);
        when(jwtService.getToken(userDetails)).thenThrow(new NullPointerException());

        // Act & Assert
        assertThrows(NullPointerException.class, () -> jwtService.getToken(userDetails));
    }

    // retrieve login username from null JWT token
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
    public void test_retrieve_login_username_from_valid_jwt_token2() {
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

    // Should return null if provided with a null JWT token
    @Test
    public void test_retrieve_login_username_from_null_jwt_token() {
        // Arrange
        JwtService jwtService = mock(JwtService.class);
        String token = null;
        when(jwtService.getLoginFromToken(token)).thenThrow(new IllegalArgumentException("Token cannot be null"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> jwtService.getLoginFromToken(token));
    }

    // Returns the expiration date of a valid JWT token.
    @Test
    public void test_returns_expiration_date_of_valid_token() {
        // Arrange
        JwtService jwtService = mock(JwtService.class);
        String token = "valid_token";
        Date expectedExpirationDate = new Date();

        when(jwtService.getExpiration(token)).thenReturn(expectedExpirationDate);

        // Act
        Date expirationDate = jwtService.getExpiration(token);


        // Assert
        Assertions.assertNotNull(expirationDate);
        // Add additional assertions if needed
    }

    // Throws an exception when provided with an invalid JWT token.
    @Test
    public void test_returns_null_with_invalid_token() {
        // Arrange
        JwtService jwtService = mock(JwtService.class);
        String invalidToken = "invalid_token";

        // Act
        Date expirationDate = jwtService.getExpiration(invalidToken);

        // Assert
        Assertions.assertNull(expirationDate);
    }

}
