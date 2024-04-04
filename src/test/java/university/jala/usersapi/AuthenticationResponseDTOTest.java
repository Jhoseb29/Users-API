package university.jala.usersapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import university.jala.usersapi.domain.models.dto.AuthenticationResponseDTO;

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


    @Test
    public void testNoArgsConstructor() {
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO();
        assertNull(responseDTO.getToken());
    }

    @Test
    public void testAllArgsConstructor() {
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO("token123");
        assertEquals("token123", responseDTO.getToken());
    }

    @Test
    public void testGetterAndSetter() {
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO();
        responseDTO.setToken("token456");
        assertEquals("token456", responseDTO.getToken());
    }

    @Test
    public void testBuilder() {
        AuthenticationResponseDTO responseDTO = AuthenticationResponseDTO.builder()
                .token("token789")
                .build();

        assertEquals("token789", responseDTO.getToken());
    }

    @Test
    public void testEqualsAndHashCode() {
        AuthenticationResponseDTO responseDTO1 = new AuthenticationResponseDTO("token123");
        AuthenticationResponseDTO responseDTO2 = new AuthenticationResponseDTO("token123");
        AuthenticationResponseDTO responseDTO3 = new AuthenticationResponseDTO("differentToken");

        assertEquals(responseDTO1, responseDTO2);
        Assertions.assertNotEquals(responseDTO1, responseDTO3);
        assertEquals(responseDTO1.hashCode(), responseDTO2.hashCode());
        Assertions.assertNotEquals(responseDTO1.hashCode(), responseDTO3.hashCode());
    }

    @Test
    public void testToString() {
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO("token123");
        String expectedString = "AuthenticationResponseDTO(token=token123)";
        assertEquals(expectedString, responseDTO.toString());
    }
}