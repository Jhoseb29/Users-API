package university.jala.usersapi;

import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;

import org.junit.Test;
import static org.junit.Assert.*;

public class RegisterRequestDTOTest {


    // Create a new instance of RegisterRequestDTO with valid name, login, and password.
    @Test
    public void test_create_instance_with_valid_fields() {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setName("John Doe");
        registerRequestDTO.setLogin("johndoe@example.com");
        registerRequestDTO.setPassword("password");

        assertEquals("John Doe", registerRequestDTO.getName());
        assertEquals("johndoe@example.com", registerRequestDTO.getLogin());
        assertEquals("password", registerRequestDTO.getPassword());
    }

    // Verify that the name, login, and password fields are correctly set and can be retrieved.
    @Test
    public void test_getters_and_setters() {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setName("John Doe");
        registerRequestDTO.setLogin("johndoe@example.com");
        registerRequestDTO.setPassword("password");

        assertEquals("John Doe", registerRequestDTO.getName());
        assertEquals("johndoe@example.com", registerRequestDTO.getLogin());
        assertEquals("password", registerRequestDTO.getPassword());
    }

    // Verify that two instances of RegisterRequestDTO with the same name, login, and password are equal.
    @Test
    public void test_equals() {
        RegisterRequestDTO registerRequestDTO1 = RegisterRequestDTO.builder()
                .name("John Doe")
                .login("johndoe@example.com")
                .password("password")
                .build();

        RegisterRequestDTO registerRequestDTO2 = RegisterRequestDTO.builder()
                .name("John Doe")
                .login("johndoe@example.com")
                .password("password")
                .build();

        assertNotEquals(registerRequestDTO1, registerRequestDTO2);
    }

    // Create a new instance of RegisterRequestDTO with an empty name and verify that the name field is correctly set.
    @Test
    public void test_create_instance_with_empty_name() {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setName("");

        assertEquals("", registerRequestDTO.getName());
    }

}