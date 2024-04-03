package university.jala.usersapi;


import university.jala.usersapi.domain.models.dto.UserDTOById;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserDTOByIdTest {


    // UserDTOById object can be created with valid input parameters
    @Test
    public void test_createUserDTOByIdWithValidInput() {
        UserDTOById user = UserDTOById.builder()
            .id("user_id")
            .name("Test User")
            .login("testuser")
            .password("password")
            .build();

        assertNotNull(user);
        assertEquals("user_id", user.getId());
        assertEquals("Test User", user.getName());
        assertEquals("testuser", user.getLogin());
        assertEquals("password", user.getPassword());
    }

    // UserDTOById object can be updated with valid input parameters
    @Test
    public void test_updateUserDTOByIdWithValidInput() {
        UserDTOById user = UserDTOById.builder()
            .id("user_id")
            .name("Test User")
            .login("testuser")
            .password("password")
            .build();

        user.setName("New Name");
        user.setLogin("newlogin");
        user.setPassword("newpassword");

        assertEquals("New Name", user.getName());
        assertEquals("newlogin", user.getLogin());
        assertEquals("newpassword", user.getPassword());
    }

    // UserDTOById object can be compared with another UserDTOById object
    @Test
    public void test_compareUserDTOByIdObjects() {
        UserDTOById user1 = UserDTOById.builder()
            .id("user_id")
            .name("Test User")
            .login("testuser")
            .password("password")
            .build();

        UserDTOById user2 = UserDTOById.builder()
            .id("user_id")
            .name("Test User")
            .login("testuser")
            .password("password")
            .build();

        assertEquals(user1, user2);
    }

    // UserDTOById object can be created with empty input parameters
    @Test
    public void test_createUserDTOByIdWithEmptyInput() {
        UserDTOById user = new UserDTOById();

        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
    }

    // UserDTOById object can be created with null input parameters
    @Test
    public void test_createUserDTOByIdWithNullInput() {
        UserDTOById user = UserDTOById.builder()
            .id(null)
            .name(null)
            .login(null)
            .password(null)
            .build();

        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
    }

    // UserDTOById object can be updated with empty input parameters
    @Test
    public void test_updateUserDTOByIdWithEmptyInput() {
        UserDTOById user = UserDTOById.builder()
            .id("user_id")
            .name("Test User")
            .login("testuser")
            .password("password")
            .build();

        user.setName("");
        user.setLogin("");
        user.setPassword("");

        assertEquals("", user.getName());
        assertEquals("", user.getLogin());
        assertEquals("", user.getPassword());
    }

    // UserDTOById object can be updated with null input parameters
    @Test
    public void test_updateUserDTOByIdWithNullInput() {
        UserDTOById user = UserDTOById.builder()
            .id("user_id")
            .name("Test User")
            .login("testuser")
            .password("password")
            .build();

        user.setName(null);
        user.setLogin(null);
        user.setPassword(null);

        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
    }

}