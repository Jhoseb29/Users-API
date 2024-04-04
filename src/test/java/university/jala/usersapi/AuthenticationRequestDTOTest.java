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
        assertEquals("username", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
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
        assertEquals("password", requestDTO.getPassword());
    }

    @Test
    public void testGetPasswordValueAfterInitialization() {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .login("username")
                .password("password")
                .build();
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

    @Test
    public void test_equalsObjets(){
        AuthenticationRequestDTO requestDTO1 = new AuthenticationRequestDTO("username", "123");
        AuthenticationRequestDTO requestDTO2 = new AuthenticationRequestDTO("username", "123");
        assertFalse(requestDTO1.equals(requestDTO2));
        assertFalse(requestDTO2.equals(requestDTO1));
    }

    @Test
    public void testNoArgsConstructor() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
        assertNull(requestDTO.getLogin());
        assertNull(requestDTO.getPassword());
    }

    @Test
    public void testAllArgsConstructor() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
        assertEquals("username", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }

    @Test
    public void testGetterAndSetter() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO();
        requestDTO.setLogin("username");
        requestDTO.setPassword("password");
        assertEquals("username", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }

    @Test
    public void testEqualsAndHashCode() {
        AuthenticationRequestDTO requestDTO1 = new AuthenticationRequestDTO("username", "password");
        AuthenticationRequestDTO requestDTO2 = new AuthenticationRequestDTO("username", "password");
        AuthenticationRequestDTO requestDTO3 = new AuthenticationRequestDTO("differentUsername", "password");

        assertEquals(requestDTO1, requestDTO2);
        assertNotEquals(requestDTO1, requestDTO3);
        assertEquals(requestDTO1.hashCode(), requestDTO2.hashCode());
        assertNotEquals(requestDTO1.hashCode(), requestDTO3.hashCode());
    }

    @Test
    public void testBuilder() {
        AuthenticationRequestDTO requestDTO = AuthenticationRequestDTO.builder()
                .login("username")
                .password("password")
                .build();

        assertEquals("username", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }
}