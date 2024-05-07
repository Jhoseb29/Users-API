package university.jala.usersapi.core.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/**
 * User entity class.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@JsonIgnoreProperties("_class")
public final class User implements UserDetails {

  /**
   * The unique ID of the user.
   */
  @Id
  @JsonIgnore
  @Field("_id")
  private String _id;

  /**
   * The name of the user.
   */
  @NotBlank(message = "The field 'name' cannot be empty.")
  @NotNull(message = "The field 'name' cannot be null.")
  private String name;

  /**
   * The login of the user.
   */
  @NotBlank(message = "The field 'login' cannot be empty.")
  @NotNull(message = "The field 'login' cannot be null.")
  private String login;

  /**
   * The password of the user.
   */
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

}
