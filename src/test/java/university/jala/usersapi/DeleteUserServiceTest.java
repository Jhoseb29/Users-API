package university.jala.usersapi;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.UserDTOById;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.persistance.repository.UserRepository;


import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteUserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testDeleteByIdService() {
        // Arrange
        String id = UUID.randomUUID().toString();
        UserDTOById userDTO = new UserDTOById(id, "John Doe","john01","12345");
        User user = new User(id, "John Doe","john01","12345");


        // Simular el comportamiento de findById
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        Optional<UserDTOById> deletedUser = userService.deleteById(id);

        // Assert
        assertTrue(deletedUser.isPresent());
        assertEquals(userDTO, deletedUser.get());

        // Verificar que se llamó a deleteById del repositorio
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteTest_userNotExistsService() {

        String id = UUID.randomUUID().toString();
        UserDTOById userDTO = new UserDTOById(id, "John Doe","john01","12345");
        User user = new User("5", "John Doe","john01","12345");


        // Simular el comportamiento de findById
        when(userRepository.findById(id)).thenReturn(Optional.of(user));


        Optional<UserDTOById> deletedUser = userService.deleteById(id);


        //noinspection OptionalGetWithoutIsPresent
        assertNotEquals(deletedUser.get().getId(), id);
        assertNotEquals(userDTO, deletedUser.get());

        // Verificar que se llamó a deleteById del repositorio
        verify(userRepository, times(1)).deleteById(id);
    }
}