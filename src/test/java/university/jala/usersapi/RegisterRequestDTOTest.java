package university.jala.usersapi;

import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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

        assertFalse(registerRequestDTO1.equals(registerRequestDTO2));
    }

    // Create a new instance of RegisterRequestDTO with an empty name and verify that the name field is correctly set.
    @Test
    public void test_create_instance_with_empty_name() {
        RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
        registerRequestDTO.setName("");

        assertEquals("", registerRequestDTO.getName());
    }

    @Test
    public void testEqualsAndHashCode() {
        RegisterRequestDTO request1 = RegisterRequestDTO.builder()
                .name("John")
                .login("john123")
                .password("password123")
                .build();

        RegisterRequestDTO request2 = request1;

        RegisterRequestDTO request3 = RegisterRequestDTO.builder()
                .name("Jane")
                .login("jane456")
                .password("password456")
                .build();

        // Test equals method
        assertEquals(request1, request2); // Equal objects
        assertNotEquals(request1, request3); // Different objects

        // Test hashCode method
        assertEquals(request1.hashCode(), request2.hashCode()); // Equal objects have equal hash codes
        assertNotEquals(request1.hashCode(), request3.hashCode()); // Different objects should have different hash codes
    }

}