package university.jala.usersapi;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import university.jala.usersapi.domain.models.User;

import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    private User user;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Before
    public void setUp() {
        user = new User();
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
        user.setId("testId"); // ID no debería ser asignado directamente, sino generado automáticamente

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        assertEquals(1, violations.size());
    }

    @Test
    public void testNameValidation() {
        // Given
        user.setName(null);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        assertEquals(1, violations.size());
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
}
