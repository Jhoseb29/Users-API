package university.jala.usersapi.domain.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Utility class for encrypting passwords.
 */
@Service
@RequiredArgsConstructor
public class PasswordEncrypterUtil {

  /**
   * The password encoder.
   */
  @Autowired
  private final PasswordEncoder passwordEncoder;

  /**
   * Encrypts the provided initial password.
   *
   * @param initialPassword The initial password to be encrypted
   * @return The encrypted password
   */
  public String encryptPassword(final String initialPassword) {
    return passwordEncoder.encode(initialPassword);
  }
}
