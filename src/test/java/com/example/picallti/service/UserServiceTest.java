package com.example.picallti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#addUser(User)}
     */
    @Test
    void testAddUser() {
        when(userRepository.save((User) any()))
                .thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
        User user = new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role");

        userService.addUser(user);
        verify(userRepository).save((User) any());
        assertEquals("Role", user.getRole());
        assertEquals("Prenom", user.getPrenom());
        assertEquals(1, user.getImageName());
        assertEquals(1, user.getPhone());
        assertEquals("iloveyou", user.getPassword());
        assertEquals("Nom", user.getNom());
        assertEquals(0, user.getId());
        assertEquals("Genre", user.getGenre());
        assertEquals("jane.doe@example.org", user.getEmail());
        assertTrue(userService.getAllUsers().isEmpty());
    }

    /**
     * Method under test: {@link UserService#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        Collection<User> actualAllUsers = userService.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserService#getUserById(int)}
     */
    @Test
    void testGetUserById() {
        Optional<User> ofResult = Optional
                .of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
       User actualUserById = userService.getUserById(1).get();
        assertSame(ofResult, actualUserById);
        //assertTrue(actualUserById.);
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserService#getUserByEmail(String)}
     */
    @Test
    void testGetUserByEmail() {
        Optional<User> ofResult = Optional
                .of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
        when(userRepository.findByEmail((String) any())).thenReturn(ofResult);
        Optional<User> actualUserByEmail = userService.getUserByEmail("jane.doe@example.org");
        assertSame(ofResult, actualUserByEmail);
        assertTrue(actualUserByEmail.isPresent());
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserService#update(User)}
     */
    @Test
    void testUpdate() {
        when(userRepository.save((User) any()))
                .thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
        when(userRepository.findById((Integer) any())).thenReturn(
                Optional.of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role")));
        userService.update(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
        verify(userRepository).save((User) any());
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserService#update(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.example.picallti.repository.UserRepository.findById(Object)" is null
        //       at com.example.picallti.service.UserService.update(UserService.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.save((User) any()))
                .thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
        when(userRepository.findById((Integer) any())).thenReturn(null);
        userService.update(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
    }

    /**
     * Method under test: {@link UserService#update(User)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.picallti.model.User.getId()" because "user" is null
        //       at com.example.picallti.service.UserService.update(UserService.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.save((User) any()))
                .thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role"));
        when(userRepository.findById((Integer) any())).thenReturn(
                Optional.of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role")));
        userService.update(null);
    }

    /**
     * Method under test: {@link UserService#removeUserById(int)}
     */
    @Test
    void testRemoveUserById() {
        doNothing().when(userRepository).deleteById((Integer) any());
        userService.removeUserById(1);
        verify(userRepository).deleteById((Integer) any());
        assertTrue(userService.getAllUsers().isEmpty());
    }
}

