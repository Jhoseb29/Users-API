package university.jala.usersapi;

import org.junit.jupiter.api.Test;
import university.jala.usersapi.domain.service.exception.WrongDataException;
import university.jala.usersapi.domain.util.RequestDataValidatorUtil;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RequestDataValidatorUtilTest {

    // Should validate a non-null and non-empty field and content without throwing an exception.
    @Test
    public void testValidateNonNullAndNonEmptyFieldAndContent() {
        RequestDataValidatorUtil validator = new RequestDataValidatorUtil();
        assertDoesNotThrow(() -> validator.validate("field", "content"));
    }

    // Should validate a non-null and non-empty field and content with spaces without throwing an exception.
    @Test
    public void testValidateNonNullAndNonEmptyFieldAndContentWithSpaces() {
        RequestDataValidatorUtil validator = new RequestDataValidatorUtil();
        assertDoesNotThrow(() -> validator.validate("field", "content with spaces"));
    }

    // Should validate a non-null and non-empty field and content with special characters without throwing an exception.
    @Test
    public void testValidateNonNullAndNonEmptyFieldAndContentWithSpecialCharacters() {
        RequestDataValidatorUtil validator = new RequestDataValidatorUtil();
        assertDoesNotThrow(() -> validator.validate("field", "content with special characters!@#$%^&*()"));
    }

    // Should validate a non-null and non-empty field and content with numbers without throwing an exception.
    @Test
    public void testValidateNonNullAndNonEmptyFieldAndContentWithNumbers() {
        RequestDataValidatorUtil validator = new RequestDataValidatorUtil();
        assertDoesNotThrow(() -> validator.validate("field", "content with numbers 12345"));
    }

    // Should validate a non-null and non-empty field and content with letters without throwing an exception.
    @Test
    public void testValidateNonNullAndNonEmptyFieldAndContentWithLetters() {
        RequestDataValidatorUtil validator = new RequestDataValidatorUtil();
        assertDoesNotThrow(() -> validator.validate("field", "content with letters ABCDE"));
    }

    // Should throw a WrongDataException when validating a null content.
    @Test
    public void testThrowExceptionWhenValidatingNullContent() {
        RequestDataValidatorUtil validator = new RequestDataValidatorUtil();
        assertThrows(WrongDataException.class, () -> validator.validate("field", null));
    }

    // Should throw a WrongDataException when validating an empty content.
    @Test
    public void testThrowExceptionWhenValidatingEmptyContent() {
        RequestDataValidatorUtil validator = new RequestDataValidatorUtil();
        assertThrows(WrongDataException.class, () -> validator.validate("field", ""));
    }

}