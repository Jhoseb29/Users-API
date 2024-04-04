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

    @Test
    public void testEquals() {
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

        RegisterRequestDTO request4 = null; // Null object

        String differentTypeObject = "This is a string"; // Different type of object

        // Reflexivity: an object must be equal to itself
        assertEquals(request1, request1);

        // Symmetry: if a.equals(b), then b.equals(a)
        assertEquals(request1.equals(request2), request2.equals(request1));

        assertTrue(request3.equals(request3));

        // Consistency: multiple invocations of equals() method must consistently return the same result
        assertEquals(request1.equals(request2), request1.equals(request2));

        // Non-nullity: for any non-null reference value x, x.equals(null) should return false
        assertFalse(request1.equals(null));

        // Equality with null object should return false
        assertFalse(request1.equals(request4));

        // Equality with a different type of object should return false
        assertFalse(request1.equals(differentTypeObject));
    }

}