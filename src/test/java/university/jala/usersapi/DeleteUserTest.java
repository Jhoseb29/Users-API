package university.jala.usersapi;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteUserTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp(){
        userController = new UserController();
        userController.setUserService(userService);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void DeleteTest(){

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Julio");
        user.setLogin("juli");
        user.setPassword("julio123");

        when(UserService.DeleteUser(any(User.class))).thenReturn(user);

        User deletingUser = userController.deleteUser("7");

        assertEquals(user.getId(), deletingUser.getId());

    }
}