package university.jala.usersapi;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import university.jala.usersapi.core.application.utils.PasswordEncrypterUtil;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;


public class PasswordEncrypterUtilTest {

    // Can encrypt a valid password
    @Test
    public void testEncryptValidPassword() {
        // Arrange
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        PasswordEncrypterUtil passwordEncrypterUtil = new PasswordEncrypterUtil(passwordEncoder);
        String initialPassword = "password123";
        String encryptedPassword = "encryptedPassword123";
        when(passwordEncoder.encode(initialPassword)).thenReturn(encryptedPassword);

        // Act
        String result = passwordEncrypterUtil.encryptPassword(initialPassword);

        // Assert
        assertEquals(encryptedPassword, result);
        verify(passwordEncoder).encode(initialPassword);
    }

    // Can encrypt an empty password
    @Test
    public void testEncryptEmptyPassword() {
        // Arrange
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        PasswordEncrypterUtil passwordEncrypterUtil = new PasswordEncrypterUtil(passwordEncoder);
        String initialPassword = "";
        String encryptedPassword = "encryptedPassword123";
        when(passwordEncoder.encode(initialPassword)).thenReturn(encryptedPassword);

        // Act
        String result = passwordEncrypterUtil.encryptPassword(initialPassword);

        // Assert
        assertEquals(encryptedPassword, result);
        verify(passwordEncoder).encode(initialPassword);
    }

    // Can encrypt a password with special characters
    @Test
    public void testEncryptPasswordWithSpecialCharacters() {
        // Arrange
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        PasswordEncrypterUtil passwordEncrypterUtil = new PasswordEncrypterUtil(passwordEncoder);
        String initialPassword = "password!@#";
        String encryptedPassword = "encryptedPassword123";
        when(passwordEncoder.encode(initialPassword)).thenReturn(encryptedPassword);

        // Act
        String result = passwordEncrypterUtil.encryptPassword(initialPassword);

        // Assert
        assertEquals(encryptedPassword, result);
        verify(passwordEncoder).encode(initialPassword);
    }

    // Returns different encrypted passwords for the same initial password
    @Test
    public void testReturnDifferentEncryptedPasswords() {
        // Arrange
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        PasswordEncrypterUtil passwordEncrypterUtil = new PasswordEncrypterUtil(passwordEncoder);
        String initialPassword = "password123";
        String encryptedPassword1 = "encryptedPassword123";
        String encryptedPassword2 = "encryptedPassword456";
        when(passwordEncoder.encode(initialPassword)).thenReturn(encryptedPassword1, encryptedPassword2);

        // Act
        String result1 = passwordEncrypterUtil.encryptPassword(initialPassword);
        String result2 = passwordEncrypterUtil.encryptPassword(initialPassword);

        // Assert
        assertNotEquals(result1, result2);
        verify(passwordEncoder, times(2)).encode(initialPassword);
    }
}