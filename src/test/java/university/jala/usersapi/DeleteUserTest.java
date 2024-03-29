package university.jala.usersapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;

import java.util.UUID;


import static org.mockito.Mockito.*;

public class DeleteUserTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void DeleteTest(){
        String UserId = UUID.randomUUID().toString();
        User user = new User();
        user.setId(UserId);
        user.setName("Julio");
        user.setLogin("juli");
        user.setPassword("julio123");
        boolean reponseExpecte = true;

       when(userService.deleteUserById(UserId)).thenReturn(reponseExpecte);
        boolean response = userController.deleteById(UserId);

        Assertions.assertEquals(reponseExpecte, response);
        verify(userService, times(1)).deleteUserById(UserId);
    }
    @Test
    public void deleteTest_userNotExists() {

        String nonExistentUserId = "90";

        when(userService.deleteUserById(nonExistentUserId)).thenReturn(any(boolean.class));

        boolean response = userController.deleteById(nonExistentUserId);

        Assertions.assertEquals(false, response);

        verify(userService, times(1)).deleteUserById(nonExistentUserId);
    }
}