package com.example.picallti.controller;

import com.example.picallti.model.Notification;
import com.example.picallti.model.User;
import com.example.picallti.repository.NotificationRepository;
import com.example.picallti.service.NotificationService;
import com.example.picallti.service.UserService;
import lombok.ToString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @Mock
    private NotificationRepository notificationRepository;

    private NotificationService notificationService;

    private NotificationController notificationController;

    private AutoCloseable autoCloseable1;

    private List notificationList;


    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable1 = MockitoAnnotations.openMocks(this);
        notificationController = new NotificationController();
        notificationService = new NotificationService();
        notificationService.notificationRepository = notificationRepository;
        notificationController.notificationService = notificationService;

    }

    @AfterEach
    void tearDown() throws Exception {
        //autoCloseable.close();
    }

    @Test
    void canAddNotification() {
        User userTest = new User();
        Notification notification = new Notification(1, "title", "text", 1, userTest, LocalTime.now(), LocalDate.of(2022, Month.JULY, 9));

        notificationController.addNotification(notification);


        ArgumentCaptor<Notification> argumentCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationRepository).save(argumentCaptor.capture());

        Notification notification1 = argumentCaptor.getValue();
        assertThat(notification1).isEqualTo(notification);
    }

    @Test
    void canFindAllNotification() {


        notificationController.getAllNotifications();
        verify(notificationRepository).findAll();
    }
/*
    @Test
    void canFindNotificationByUser() throws Exception{

        User user = new User();

        //List<Notification> mockNotification = (List<Notification>) new Notification(1, "title", "text", 1, user, LocalTime.now(), LocalDate.of(2022, Month.JULY, 9));
        Notification mockNotification = new Notification(1, "title", "text", 1, user, LocalTime.now(), LocalDate.of(2022, Month.JULY, 9));
        lenient().when(notificationRepository.existsById(user.getId())).thenReturn(true);
        //lenient().when(notificationRepository.findById(user.getId())).thenReturn(Optional.of(mockNotification));
        doReturn(Optional.of(mockNotification)).when(notificationRepository.findById(user.getId()));


        List<Notification> notification = (List<Notification>) notificationController.getNotificationByUser(user.getId());
        assertEquals(mockNotification, notification);
/*

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
         */

}

/*
    @Test
    void canFindNotificationByUser() throws Exception{

        List<Notification> mockNotification = (List<Notification>) new Notification();
        User user = new User();
        mockNotification.;
        lenient().when(notificationRepository.existsById(user.getId())).thenReturn(true);
        lenient().when(notificationRepository.findById(user.getId())).thenReturn(Optional.of(mockNotification));


        List<Notification> notification = (List<Notification>) notificationController.getNotificationByUser(user.getId());
        assertEquals(mockNotification, notification);


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
         */

        /*
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

    }*/
