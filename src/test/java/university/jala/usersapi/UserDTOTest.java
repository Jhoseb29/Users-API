package university.jala.usersapi;

import university.jala.usersapi.domain.models.dto.UserDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserDTOTest {

    // UserDTO object can be created with valid input parameters
    @Test
    public void testCreateUserDTOWithValidInput() {
        UserDTO userDTO = new UserDTO("1", "John Doe", "johndoe");
        assertNotNull(userDTO);
        assertEquals("1", userDTO.getId());
        assertEquals("John Doe", userDTO.getName());
        assertEquals("johndoe", userDTO.getLogin());
    }

    // UserDTO object can be updated with valid input parameters
    @Test
    public void testUpdateUserDTOWithValidInput() {
        UserDTO userDTO = new UserDTO("1", "John Doe", "johndoe");
        userDTO.setId("2");
        userDTO.setName("Jane Smith");
        userDTO.setLogin("janesmith");
        assertEquals("2", userDTO.getId());
        assertEquals("Jane Smith", userDTO.getName());
        assertEquals("janesmith", userDTO.getLogin());
    }

    // UserDTO object can be compared with another UserDTO object with same input parameters
    @Test
    public void testCompareUserDTOWithSameInputParameters() {
        UserDTO userDTO1 = new UserDTO("1", "John Doe", "johndoe");
        UserDTO userDTO2 = new UserDTO("1", "John Doe", "johndoe");
        assertEquals(userDTO1, userDTO2);
    }

    // UserDTO object can be created with null input parameters
    @Test
    public void testCreateUserDTOWithNullInput() {
        UserDTO userDTO = new UserDTO(null, null, null);
        assertNotNull(userDTO);
        assertNull(userDTO.getId());
        assertNull(userDTO.getName());
        assertNull(userDTO.getLogin());
    }

    // UserDTO object can be updated with null input parameters
    @Test
    public void testUpdateUserDTOWithNullInput() {
        UserDTO userDTO = new UserDTO("1", "John Doe", "johndoe");
        userDTO.setId(null);
        userDTO.setName(null);
        userDTO.setLogin(null);
        assertNull(userDTO.getId());
        assertNull(userDTO.getName());
        assertNull(userDTO.getLogin());
    }

    // UserDTO object can be compared with another UserDTO object with different input parameters
    @Test
    public void testCompareUserDTOWithDifferentInputParameters() {
        UserDTO userDTO1 = new UserDTO("1", "John Doe", "johndoe");
        UserDTO userDTO2 = new UserDTO("2", "Jane Smith", "janesmith");
        assertNotEquals(userDTO1, userDTO2);
    }

    // UserDTO object can be compared with null object
    @Test
    public void testCompareUserDTOWithNullObject() {
        UserDTO userDTO = new UserDTO("1", "John Doe", "johndoe");
        assertNotEquals(userDTO, null);
    }

    @Test
    public void testHashCode() {

        UserDTO user1 = UserDTO.builder()
                .id("123")
                .name("John Doe")
                .login("johndoe")
                .build();

        UserDTO user2 = user1;

        UserDTO user3 = UserDTO.builder()
                .id("456")
                .name("Jane Smith")
                .login("janesmith")
                .build();

        // Verificar que los hashCode de ambas instancias son iguales
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(0, user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
}