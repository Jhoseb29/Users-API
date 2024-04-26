package university.jala.usersapi;

import org.junit.jupiter.api.Test;
import university.jala.usersapi.core.domain.exceptions.WrongDataException;

import static org.junit.Assert.assertEquals;

public class WrongDataExceptionTest {

    @Test
    public void testConstructor() {
        // Given
        String message = "This is a test message";

        // When
        WrongDataException exception = new WrongDataException(message);

        // Then
        assertEquals(message, exception.getMessage());
    }
}
