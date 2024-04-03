package university.jala.usersapi;

import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AuthenticationRequestDTOTest {


    // Create an instance of AuthenticationRequestDTO with valid login and password.
    @Test
    public void test_create_instance_with_valid_login_and_password() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
        assertEquals("username", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }

    // Verify that the instance of AuthenticationRequestDTO has the correct login and password values.
    @Test
    public void test_verify_correct_login_and_password_values() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
        assertTrue(requestDTO.getLogin().equals("username"));
        assertTrue(requestDTO.getPassword().equals("password"));
    }

    // Create an instance of AuthenticationRequestDTO with empty login and valid password.
    @Test
    public void test_create_instance_with_empty_login_and_valid_password() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("", "password");
        assertEquals("", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }

    // Create an instance of AuthenticationRequestDTO with valid login and empty password.
    @Test
    public void test_create_instance_with_valid_login_and_empty_password() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "");
        assertEquals("username", requestDTO.getLogin());
        assertEquals("", requestDTO.getPassword());
    }

    @Test
    public void test_create_instance_with_null_login_and_valid_password() {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .login(null)
                .password("password")
                .build();
        assertNull(requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword().toString());
    }

    @Test
    public void testGetPasswordValueAfterInitialization() {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .login("username")
                .password("password")
                .build();
        System.out.println(requestDTO.toString());
        assertEquals("password", requestDTO.getPassword());
    }

    @Test
    public void testCorrectStructureRequestDTO() {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .login("username")
                .password("password")
                .build();
        System.out.println(requestDTO.toString());
        assertEquals("AuthenticationRequestDTO(login=username, password=password)", requestDTO.toString());
    }

}