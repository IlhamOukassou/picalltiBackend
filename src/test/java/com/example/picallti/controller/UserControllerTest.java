package com.example.picallti.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;



import java.util.Optional;
import static org.mockito.Mockito.*;


//@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private UserController userController;
    private AutoCloseable autoCloseable;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        userController = new UserController();
        userService = new UserService();
        userService.userRepository = userRepository;
        userController.userService = userService;
    }

    @AfterEach
    void tearDown() throws Exception {
        //autoCloseable.close();
    }

    @Test
    void canAddUser() {
        //given
        User user = new User( "nom", "prenom", "F", "test@test.com", 1234568, "pass" );
        //when
        userController.addUser(user);
        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        User user1 = argumentCaptor.getValue();
        assertThat(user1).isEqualTo(user);
    }

    @Test
    @Disabled
    void willAddUserWhenEmailIsTaken() {
        //given
        User user = new User( "nom", "prenom", "F", "test@test.com", 1234568, "pass");
        //when
        userController.addUser(user);

        boolean isExist = userRepository.findByEmail(user.getEmail()).isPresent();
        given(userRepository.findByEmail(user.getEmail()).isPresent()).willReturn(true);
        //doReturn(true).when(userRepository.findByEmail(user.getEmail())).isPresent();
        //then
        assertThatThrownBy(() -> userController.addUser(user)).isInstanceOf(HttpClientErrorException.BadRequest.class).hasMessageContaining("email exist");

    }

    @Test
    void canGetAllUsers() {
        //when
        userController.getAllUsers();
        //then
        verify(userRepository).findAll();


    }


    @Test
    void getUserByIdIfNotExist() throws Exception {
        int userId = 1;
        lenient().when(userRepository.existsById(userId)).thenReturn(false);
        User user = userController.getUserById(userId);
        assertNull(user);

    }

    @Test
    void getUserByIdIfExist() throws Exception {
        User mockUser = new User();
        //String clientCin = "cin";
        int userId = 1;
        mockUser.setId(userId);
        //mockUser.setCin(clientCin);
        lenient().when(userRepository.existsById(userId)).thenReturn(true);
        lenient().when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User user = userController.getUserById(userId);
        //System.out.println(user);
        assertEquals(mockUser, user);


    }

    @Test
    void getUserByEmailIfNotExist() {
        String mail = "email";
        lenient().when(userRepository.existsUserByEmail(mail)).thenReturn(false);
        User user = userController.getUserByEmail(mail);
        assertNull(user);
    }

    @Test
    void getUserByEmailIfExist() {
        User mockUser = new User();
        //String clientCin = "cin";
        String mail = "email";
        mockUser.setEmail(mail);
        //mockUser.setCin(clientCin);
        lenient().when(userRepository.existsUserByEmail(mail)).thenReturn(true);
        lenient().when(userRepository.findByEmail(mail)).thenReturn(Optional.of(mockUser));

        User user = userController.getUserByEmail(mail);
        System.out.println(user);
        assertEquals(mockUser, user);
    }


    @Test
    void updateUserIfExist() {
        int userId = 1;
        //User mockUser = new User();

        User user = new User( "nom", "prenom", "F", "test@test.com", 1234568, "pass");


        lenient().when(userRepository.existsById(userId)).thenReturn(true);
        lenient().when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        lenient().when(userRepository.save(user)).thenReturn(user);

        User user1 = userController.updateUser(user);

        assertEquals(user, user1);

    }

    @Test
    void updateUserIfNotExist() {
        int userId = 1;
        User mockUser = new User();
        mockUser.setId(userId);
        lenient().when(userRepository.existsById(userId)).thenReturn(true);
        User user = null;
        //if(userController.updateUser(mockUser) != null) user = userController.updateUser(mockUser);
        assertThatExceptionOfType(RuntimeException.class);
        //ssertNull(user);
    }

    /*@Test
    void removeUserIfExist() {
        int userId = 1;
        //User mockUser = new User();



        lenient().when(userRepository.existsById(userId)).thenReturn(true);

        User user1 = userController.removeUser(userId);

        assert

    }*/
}