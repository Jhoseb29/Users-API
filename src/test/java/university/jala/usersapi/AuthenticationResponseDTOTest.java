package university.jala.usersapi;

import university.jala.usersapi.domain.models.dto.AuthenticationResponseDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class AuthenticationResponseDTOTest {


    // The class should have a constructor that takes in a string parameter for the JWT token and sets it as the 'token' attribute.
    @Test
    public void test_constructor_with_token_parameter() {
        String token = "jwtToken";
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(token);
        assertEquals(token, responseDTO.getToken());
    }

    // The class should have a getter method for the 'token' attribute that returns the JWT token.
    @Test
    public void test_getter_for_token_attribute() {
        String token = "jwtToken";
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(token);
        assertEquals(token, responseDTO.getToken());
    }

    // The 'token' attribute can be null, and the class should handle this case without throwing any exceptions.
    @Test
    public void test_null_token_attribute() {
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(null);
        assertNull(responseDTO.getToken());
    }

    // The 'token' attribute can be an empty string, and the class should handle this case without throwing any exceptions.
    @Test
    public void test_empty_token_attribute() {
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO("");
        assertEquals("", responseDTO.getToken());
    }

}