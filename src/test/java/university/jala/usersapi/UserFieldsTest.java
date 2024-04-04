package university.jala.usersapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import university.jala.usersapi.domain.util.UserFields;

public class UserFieldsTest {


    // Enum values can be accessed and used correctly.
    @Test
    public void test_enum_values_accessed_correctly() {
        Assertions.assertEquals("name", UserFields.USER_NAME_FIELD.getField());
        Assertions.assertEquals("login", UserFields.USER_LOGIN_FIELD.getField());
        Assertions.assertEquals("password", UserFields.USER_PASSWORD_FIELD.getField());
    }


    // Enum values can be used as constants in the code.
    @Test
    public void test_enum_values_used_as_constants() {
        Assertions.assertEquals("name", UserFields.USER_NAME_FIELD.getField());
        Assertions.assertEquals("login", UserFields.USER_LOGIN_FIELD.getField());
        Assertions.assertEquals("password", UserFields.USER_PASSWORD_FIELD.getField());
    }

    // Enum values have a unique field name associated with them.
    @Test
    public void test_enum_values_unique_field_name() {
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD.getField(), UserFields.USER_LOGIN_FIELD.getField());
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD.getField(), UserFields.USER_PASSWORD_FIELD.getField());
        Assertions.assertNotEquals(UserFields.USER_LOGIN_FIELD.getField(), UserFields.USER_PASSWORD_FIELD.getField());
    }

    // Enum values cannot be null.
    @Test
    public void test_enum_values_not_null() {
        Assertions.assertNotNull(UserFields.USER_NAME_FIELD);
        Assertions.assertNotNull(UserFields.USER_LOGIN_FIELD);
        Assertions.assertNotNull(UserFields.USER_PASSWORD_FIELD);
    }

    // Enum values cannot have duplicate field names.
    @Test
    public void test_enum_values_no_duplicate_field_names() {
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD.getField(), UserFields.USER_LOGIN_FIELD.getField());
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD.getField(), UserFields.USER_PASSWORD_FIELD.getField());
        Assertions.assertNotEquals(UserFields.USER_LOGIN_FIELD.getField(), UserFields.USER_PASSWORD_FIELD.getField());
    }

    // Enum values cannot have duplicate values.
    @Test
    public void test_enum_values_no_duplicate_values() {
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD, UserFields.USER_LOGIN_FIELD);
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD, UserFields.USER_PASSWORD_FIELD);
        Assertions.assertNotEquals(UserFields.USER_LOGIN_FIELD, UserFields.USER_PASSWORD_FIELD);
    }

    // Enum values can be compared for equality.
    @Test
    public void test_enum_values_equality() {
        Assertions.assertEquals(UserFields.USER_NAME_FIELD, UserFields.USER_NAME_FIELD);
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD, UserFields.USER_LOGIN_FIELD);
        Assertions.assertNotEquals(UserFields.USER_NAME_FIELD, UserFields.USER_PASSWORD_FIELD);
    }

}