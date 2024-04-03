package university.jala.usersapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import university.jala.usersapi.domain.models.User;

import java.util.Collection;

public class UserTest {

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
}
