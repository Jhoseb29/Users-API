package university.jala.usersapi;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import university.jala.usersapi.core.domain.models.entities.User;

import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    private User user;
    private final Validator validator;

    public UserTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("testUser");
        user.setLogin("test@example");
        user.setPassword("test123");
        user.setId("777");
    }

    @Test
    public void testGetUsername() {
        // Given
        String expectedUsername = "testUser";
        User user = User.builder()
                .login(expectedUsername)
                .build();

        // When
        String actualUsername = user.getUsername();

        // Then
        Assertions.assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testIsAccountNonExpired() {
        // Given
        User user = new User();

        // When
        boolean isAccountNonExpired = user.isAccountNonExpired();

        // Then
        Assertions.assertTrue(isAccountNonExpired);
    }

    @Test
    public void testIsAccountNonLocked() {
        // Given
        User user = new User();

        // When
        boolean isAccountNonLocked = user.isAccountNonLocked();

        // Then
        Assertions.assertTrue(isAccountNonLocked);
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Given
        User user = new User();

        // When
        boolean isCredentialsNonExpired = user.isCredentialsNonExpired();

        // Then
        Assertions.assertTrue(isCredentialsNonExpired);
    }

    @Test
    public void testIsEnabled() {
        // Given
        User user = new User();

        // When
        boolean isEnabled = user.isEnabled();

        // Then
        Assertions.assertTrue(isEnabled);
    }

    @Test
    public void testGetAuthorities() {
        // Given
        User user = new User();

        // When
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Then
        Assertions.assertTrue(authorities.isEmpty());
    }

    @Test
    public void testIdField() {
        // Given
        String id = "testId";

        // When
        user.setId(id);

        // Then
        assertEquals(id, user.getId());
    }

    @Test
    public void testNameField() {
        // Given
        String name = "testName";

        // When
        user.setName(name);

        // Then
        assertEquals(name, user.getName());
    }

    @Test
    public void testLoginField() {
        // Given
        String login = "testLogin";

        // When
        user.setLogin(login);

        // Then
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testIdValidation() {
        // Given
        user.setId("testId");

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        assertEquals(0, violations.size());
    }

    @Test
    public void testNameValidation() {
        // Given
        user.setName(null);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        assertEquals(2, violations.size());
    }

    @Test
    public void testLoginValidation() {
        // Given
        user.setLogin("");

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        assertEquals(1, violations.size());
    }

    @Test
    public void testDataAnnotation() {
        // Given
        String id = "testId";
        String name = "John Doe";
        String login = "johndoe@example.com";
        String password = "password123";

        // When
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);

        // Then
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testBuilderAnnotation() {
        // Given
        String id = "testId";
        String name = "John Doe";
        String login = "johndoe@example.com";
        String password = "password123";

        // When
        User user = User.builder()
                .id(id)
                .name(name)
                .login(login)
                .password(password)
                .build();

        // Then
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testEquals() {
        // Given
        User user1 = new User("1", "John", "john@example.com", "password123");
        User user2 = new User("1", "John", "john@example.com", "password123");

        // Then
        assertEquals(user1, user2);
    }

    @Test
    public void testHashCode() {
        // Given
        User user1 = new User("1", "John", "john@example.com", "password123");
        User user2 = new User("1", "John", "john@example.com", "password123");

        // Then
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
