package university.jala.usersapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.UserDTOById;
import university.jala.usersapi.domain.service.exception.WrongDataException;
import university.jala.usersapi.domain.util.DataValidator;
import university.jala.usersapi.domain.util.UpdatableValidationUtil;


import university.jala.usersapi.domain.util.UserFields;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UpdatableValidationUtilTest {

    // Should update user name, login and password when all fields are present in the request and valid
    @Test
    public void testUpdateAllFieldsValid() throws WrongDataException {
        // Arrange
        User existingUser = User.builder().id("1").name("John").login("john123").password("password123").build();
        UserDTOById request = UserDTOById.builder().id("1").name("John Doe").login("john_doe").password("newpassword").build();
        DataValidator dataValidator = mock(DataValidator.class);
        UpdatableValidationUtil updatableValidationUtil = new UpdatableValidationUtil(dataValidator);

        // Act
        updatableValidationUtil.validateFieldsToUpdate(existingUser, request);

        // Assert
        Assertions.assertEquals("John Doe", existingUser.getName());
        Assertions.assertEquals("john_doe", existingUser.getLogin());
        Assertions.assertEquals("newpassword", existingUser.getPassword());
        verify(dataValidator, times(1)).validate(UserFields.USER_NAME_FIELD.getField(), "John Doe");
        verify(dataValidator, times(1)).validate(UserFields.USER_LOGIN_FIELD.getField(), "john_doe");
        verify(dataValidator, times(1)).validate(UserFields.USER_PASSWORD_FIELD.getField(), "newpassword");
    }

    // Should update user name, login and password when some fields are present in the request and valid
    @Test
    public void testUpdateSomeFieldsValid() throws WrongDataException {
        // Arrange
        User existingUser = User.builder().id("1").name("John").login("john123").password("password123").build();
        UserDTOById request = UserDTOById.builder().id("1").name("John Doe").build();
        DataValidator dataValidator = mock(DataValidator.class);
        UpdatableValidationUtil updatableValidationUtil = new UpdatableValidationUtil(dataValidator);

        // Act
        updatableValidationUtil.validateFieldsToUpdate(existingUser, request);

        // Assert
        Assertions.assertEquals("John Doe", existingUser.getName());
        Assertions.assertEquals("john123", existingUser.getLogin());
        Assertions.assertEquals("password123", existingUser.getPassword());
        verify(dataValidator, times(1)).validate(UserFields.USER_NAME_FIELD.getField(), "John Doe");
        verify(dataValidator, never()).validate(UserFields.USER_LOGIN_FIELD.getField(), "");
        verify(dataValidator, never()).validate(UserFields.USER_PASSWORD_FIELD.getField(), "");
    }

    // Should not update user name, login and password when none of the fields are present in the request
    @Test
    public void testNoFieldsToUpdate() throws WrongDataException {
        // Arrange
        User existingUser = User.builder().id("1").name("John").login("john123").password("password123").build();
        UserDTOById request = UserDTOById.builder().id("1").build();
        DataValidator dataValidator = mock(DataValidator.class);
        UpdatableValidationUtil updatableValidationUtil = new UpdatableValidationUtil(dataValidator);

        // Act
        updatableValidationUtil.validateFieldsToUpdate(existingUser, request);

        // Assert
        Assertions.assertEquals("John", existingUser.getName());
        Assertions.assertEquals("john123", existingUser.getLogin());
        Assertions.assertEquals("password123", existingUser.getPassword());
        verify(dataValidator, never()).validate(anyString(), anyString());
    }


    // Should not update user name, login and password when all fields are present in the request and some are invalid
    @Test
    public void testAllFieldsSomeInvalid() throws WrongDataException {
        // Arrange
        User existingUser = User.builder().id("1").name("John").login("john123").password("password123").build();
        UserDTOById request = UserDTOById.builder().id("1").name("John Doe").login("john_doe").password("newpassword").build();
        DataValidator dataValidator = mock(DataValidator.class);
        UpdatableValidationUtil updatableValidationUtil = new UpdatableValidationUtil(dataValidator);
        doThrow(WrongDataException.class).when(dataValidator).validate(UserFields.USER_NAME_FIELD.getField(), "John Doe");
        doThrow(WrongDataException.class).when(dataValidator).validate(UserFields.USER_LOGIN_FIELD.getField(), "john_doe");

        // Act & Assert
        assertThrows(WrongDataException.class, () -> updatableValidationUtil.validateFieldsToUpdate(existingUser, request));

        Assertions.assertEquals("john123", existingUser.getLogin());
        Assertions.assertEquals("password123", existingUser.getPassword());
        verify(dataValidator, times(1)).validate(UserFields.USER_NAME_FIELD.getField(), "John Doe");
        verify(dataValidator, never()).validate(UserFields.USER_PASSWORD_FIELD.getField(), "");
    }

    // Should throw WrongDataException when user name is invalid
    @Test
    public void testInvalidUserName() throws WrongDataException {
        // Arrange
        User existingUser = User.builder().id("1").name("John").login("john123").password("password123").build();
        UserDTOById request = UserDTOById.builder().id("1").name("John Doe").build();
        DataValidator dataValidator = mock(DataValidator.class);
        UpdatableValidationUtil updatableValidationUtil = new UpdatableValidationUtil(dataValidator);
        doThrow(WrongDataException.class).when(dataValidator).validate(UserFields.USER_NAME_FIELD.getField(), "John Doe");

        // Act & Assert
        assertThrows(WrongDataException.class, () -> updatableValidationUtil.validateFieldsToUpdate(existingUser, request));
        Assertions.assertEquals("John Doe", existingUser.getName());
        Assertions.assertEquals("john123", existingUser.getLogin());
        Assertions.assertEquals("password123", existingUser.getPassword());
        verify(dataValidator, times(1)).validate(UserFields.USER_NAME_FIELD.getField(), "John Doe");
    }

    // Should throw WrongDataException when user login is invalid
    @Test
    public void testWithOneOrTwoDates() throws WrongDataException {
        // Arrange
        User existingUser = User.builder().id("1").name("John").login("john123").password("password123").build();
        UserDTOById request = UserDTOById.builder().id("1").login("john_doe").build();
        DataValidator dataValidator = mock(DataValidator.class);
        UpdatableValidationUtil updatableValidationUtil = new UpdatableValidationUtil(dataValidator);
        doThrow(WrongDataException.class).when(dataValidator).validate(UserFields.USER_LOGIN_FIELD.getField(), "john_doe");

        // Act & Assert
        assertThrows(WrongDataException.class, () -> updatableValidationUtil.validateFieldsToUpdate(existingUser, request));
        Assertions.assertEquals("John", existingUser.getName());
        Assertions.assertEquals("john_doe", existingUser.getLogin());
        Assertions.assertEquals("password123", existingUser.getPassword());
    }
}