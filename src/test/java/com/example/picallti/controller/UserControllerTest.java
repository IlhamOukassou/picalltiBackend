package com.example.picallti.controller;

import com.example.picallti.dto.LoginRequestDTO;
import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.html.Option;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @Mock
    private UserService userService;
    private UserController userController;


    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }

    @AfterEach
    void tearDown() throws Exception {
        //autoCloseable.close();
    }


    @Test
    void GetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        Collection<User> mockUsers = new ArrayList<>();
        mockUsers.addAll(List.of(user1,user2,user3));
        when(userService.getAllUsers()).thenReturn(mockUsers);
        Collection<User> users = userController.getAllUsers();
        assertEquals(mockUsers,users);
        verify(userService).getAllUsers();
    }
    @Test
    void GetUserById(){
        int userId = 1;
        User mockUser = new User(userId,"nom","prenom","H","email",123,"mdp",null,"bio","role");
        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));
        User user = userController.getUserById(userId);
        assertEquals(mockUser,user);

    }
    @Test
    void GetUserByEmail(){
        String emailtest = "emailtest";
        User mockUser = new User("Fadili","Ayoub","Homme",emailtest,123,"mdp");
        when(userService.getUserByEmail(emailtest)).thenReturn(Optional.of(mockUser));
        User user = userController.getUserByEmail(emailtest);
        assertEquals(mockUser,user);
    }

    @Disabled
    @Test
    void deleteUserById(){
        int userId = 1;
        User mockUser = new User(userId,"nom","prenom","H","email",123,"mdp",null,"bio","role");
        doNothing().when(userService).removeUserById(userId);
        userController.deleteUserById(userId);
        verify(userService).removeUserById(userId);
    }

    @Test
    void addUser(){
        User mockUser = new User(1,"nom","prenom","H","email",123,"mdp",null,"bio","role");
        doNothing().when(userService).addUser(mockUser);
        userController.addUser(mockUser);
        verify(userService).addUser(mockUser);
    }

    @Disabled
    @Test
    void updateUser(){
        int userId = 1;
        User mockUser = new User(userId,"nom","prenom","H","email",123,"mdp",null,"bio","role");
        doNothing().when(userService).update(mockUser);
        userController.updateUser(mockUser);
        verify(userService).update(mockUser);
    }

    @Disabled
    @Test
    void existsUserByEmail(){
        String emailtest = "emailtest";
        User mockIsUserPresent = new User("Fadili","Ayoub","Homme",emailtest,123,"mdp");
        when(userService.getUserByEmail(emailtest).isPresent()).thenReturn(true);
        Boolean IsUserPresent = userController.existsUserByEmail(emailtest);
        assertEquals(mockIsUserPresent,IsUserPresent);
    }
    @Test
    void loginUser(){
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("email");
        loginRequestDTO.setPassword("mdp");
        User mockUser = new User("Fadili","Ayoub","Homme","email",123,"mdp");
        when(userService.loginUser(loginRequestDTO)).thenReturn(Optional.of(mockUser));
        Optional<User> user = userController.loginUser(loginRequestDTO).getBody();
        assertEquals(Optional.of(mockUser),user);
    }



}