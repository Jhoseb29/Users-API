package university.jala.usersapi;

import university.jala.usersapi.core.domain.models.dto.response.UserGetAllResponseDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserGetAllResponseDTOTest {

    // UserGetAllResponseDTO object can be created with valid input parameters
    @Test
    public void testCreateUserDTOWithValidInput() {
        UserGetAllResponseDTO userGetAllResponseDTO = new UserGetAllResponseDTO("1", "John Doe", "johndoe");
        assertNotNull(userGetAllResponseDTO);
        assertEquals("1", userGetAllResponseDTO.getId());
        assertEquals("John Doe", userGetAllResponseDTO.getName());
        assertEquals("johndoe", userGetAllResponseDTO.getLogin());
    }

    // UserGetAllResponseDTO object can be updated with valid input parameters
    @Test
    public void testUpdateUserDTOWithValidInput() {
        UserGetAllResponseDTO userGetAllResponseDTO = new UserGetAllResponseDTO("1", "John Doe", "johndoe");
        userGetAllResponseDTO.setId("2");
        userGetAllResponseDTO.setName("Jane Smith");
        userGetAllResponseDTO.setLogin("janesmith");
        assertEquals("2", userGetAllResponseDTO.getId());
        assertEquals("Jane Smith", userGetAllResponseDTO.getName());
        assertEquals("janesmith", userGetAllResponseDTO.getLogin());
    }

    // UserGetAllResponseDTO object can be compared with another UserGetAllResponseDTO object with same input parameters
    @Test
    public void testCompareUserDTOWithSameInputParameters() {
        UserGetAllResponseDTO userGetAllResponseDTO1 = new UserGetAllResponseDTO("1", "John Doe", "johndoe");
        UserGetAllResponseDTO userGetAllResponseDTO2 = new UserGetAllResponseDTO("1", "John Doe", "johndoe");
        assertEquals(userGetAllResponseDTO1, userGetAllResponseDTO2);
    }

    // UserGetAllResponseDTO object can be created with null input parameters
    @Test
    public void testCreateUserDTOWithNullInput() {
        UserGetAllResponseDTO userGetAllResponseDTO = new UserGetAllResponseDTO(null, null, null);
        assertNotNull(userGetAllResponseDTO);
        assertNull(userGetAllResponseDTO.getId());
        assertNull(userGetAllResponseDTO.getName());
        assertNull(userGetAllResponseDTO.getLogin());
    }

    // UserGetAllResponseDTO object can be updated with null input parameters
    @Test
    public void testUpdateUserDTOWithNullInput() {
        UserGetAllResponseDTO userGetAllResponseDTO = new UserGetAllResponseDTO("1", "John Doe", "johndoe");
        userGetAllResponseDTO.setId(null);
        userGetAllResponseDTO.setName(null);
        userGetAllResponseDTO.setLogin(null);
        assertNull(userGetAllResponseDTO.getId());
        assertNull(userGetAllResponseDTO.getName());
        assertNull(userGetAllResponseDTO.getLogin());
    }

    // UserGetAllResponseDTO object can be compared with another UserGetAllResponseDTO object with different input parameters
    @Test
    public void testCompareUserDTOWithDifferentInputParameters() {
        UserGetAllResponseDTO userGetAllResponseDTO1 = new UserGetAllResponseDTO("1", "John Doe", "johndoe");
        UserGetAllResponseDTO userGetAllResponseDTO2 = new UserGetAllResponseDTO("2", "Jane Smith", "janesmith");
        assertNotEquals(userGetAllResponseDTO1, userGetAllResponseDTO2);
    }

    // UserGetAllResponseDTO object can be compared with null object
    @Test
    public void testCompareUserDTOWithNullObject() {
        UserGetAllResponseDTO userGetAllResponseDTO = new UserGetAllResponseDTO("1", "John Doe", "johndoe");
        assertNotEquals(userGetAllResponseDTO, null);
    }

    @Test
    public void testHashCode() {

        UserGetAllResponseDTO user1 = UserGetAllResponseDTO.builder()
                .id("123")
                .name("John Doe")
                .login("johndoe")
                .build();

        UserGetAllResponseDTO user2 = user1;

        UserGetAllResponseDTO user3 = UserGetAllResponseDTO.builder()
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