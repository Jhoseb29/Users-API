package university.jala.usersapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.service.UserService;
import university.jala.usersapi.presentation.controller.UserController;

import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
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


       when(userService.deleteById(UserId)).thenReturn(Optional.of(user));

       ResponseEntity<?> response = userController.deleteById(UserId);

       assertEquals(HttpStatus.OK, response.getStatusCode());
       verify(userService, times(1)).deleteById(UserId);
    }
    @Test
    public void deleteTest_userNotExists() {

        String nonExistentUserId = "90";

        when(userService.deleteById(nonExistentUserId)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.getUserById(nonExistentUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(nonExistentUserId);
    }
}