package university.jala.usersapi.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User entity class.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints
        = {@UniqueConstraint(columnNames = {"login"})})
public final class User implements UserDetails {

    /**
     * ID Max Length.
     */
    public static final int MAX_ID_LENGTH_VALUE = 36;

    /**
     * Name Max Length.
     */
    public static final int MAX_NAME_LENGTH_VALUE = 200;

    /**
     * Login Max Length.
     */
    public static final int MAX_LOGIN_LENGTH_VALUE = 20;

    /**
     * Password Max Length.
     */
    public static final int MAX_PASSWORD_LENGTH_VALUE = 100;

    /**
     * The unique ID of the user.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = MAX_ID_LENGTH_VALUE)
    private String id;

    /**
     * The name of the user.
     */
    @Column(nullable = false, length = MAX_NAME_LENGTH_VALUE)
    @NotBlank(message = "The field 'name' cannot be empty.")
    @NotNull(message = "The field 'name' cannot be null.")
    private String name;

    /**
     * The login of the user.
     */
    @Column(nullable = false, length = MAX_LOGIN_LENGTH_VALUE)
    @NotBlank(message = "The field 'login' cannot be empty.")
    @NotNull(message = "The field 'login' cannot be null.")
    private String login;

    /**
     * The password of the user.
     */
    @Column(nullable = false, length = MAX_PASSWORD_LENGTH_VALUE)
    @NotBlank(message = "The field 'password' cannot be empty.")
    @NotNull(message = "The field 'password' cannot be null.")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("User{id='%s', name='%s', login='%s', password='%s'}",
                id, name, login, password);
    }

}
