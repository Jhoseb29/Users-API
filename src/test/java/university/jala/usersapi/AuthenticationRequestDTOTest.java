package university.jala.usersapi;

import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AuthenticationRequestDTOTest {


    // Create an instance of AuthenticationRequestDTO with valid login and password.
    @Test
    public void testCreateInstanceWithValidLoginAndPassword() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
        assertEquals("username", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }

    // Verify that the instance of AuthenticationRequestDTO has the correct login and password values.
    @Test
    public void testVerifyCorrectLoginAndPasswordValues() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "password");
        assertEquals("username", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }

    // Create an instance of AuthenticationRequestDTO with empty login and valid password.
    @Test
    public void testCreateInstanceWithEmptyLoginAndValidPassword() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("", "password");
        assertEquals("", requestDTO.getLogin());
        assertEquals("password", requestDTO.getPassword());
    }

    // Create an instance of AuthenticationRequestDTO with valid login and empty password.
    @Test
    public void testCreateInstanceWithValidLoginAndEmptyPassword() {
        AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("username", "");
        assertEquals("username", requestDTO.getLogin());
        assertEquals("", requestDTO.getPassword());
    }

    @Test
    public void testCreateInstanceWithNullLoginAndValidPassword() {
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
    public void testEqualHashCodes() {
        // Crear dos instancias de AuthenticationRequestDTO con los mismos valores
        AuthenticationRequestDTO request1 = AuthenticationRequestDTO.builder()
                .login("user1")
                .password("password1")
                .build();

        AuthenticationRequestDTO request2 = request1;

        AuthenticationRequestDTO request3 = AuthenticationRequestDTO.builder()
                .login("user1")
                .password("password1")
                .build();

        // Verificar que los hashCode de ambas instancias son iguales
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1.hashCode(), request3.hashCode());
        assertNotEquals(0, request2.hashCode());
    }

}