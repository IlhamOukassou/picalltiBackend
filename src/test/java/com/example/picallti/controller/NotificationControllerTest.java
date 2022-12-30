package com.example.picallti.controller;

import com.example.picallti.model.Notification;
import com.example.picallti.model.User;
import com.example.picallti.repository.NotificationRepository;
import com.example.picallti.service.NotificationService;
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

    private AutoCloseable autoCloseable;



    @BeforeEach
    void setUp(){
        AutoCloseable autoCloseable1 = MockitoAnnotations.openMocks(this);
        notificationController = new NotificationController();
        notificationService = new NotificationService();
        notificationService.notificationRepository = notificationRepository;
        notificationController.notificationService = notificationService;

    }

    @AfterEach
    void tearDown() throws Exception{
        //autoCloseable.close();
    }

    @Test
    void canAddNotification(){
        User userTest = new User();
        Notification notification = new Notification(1, "title", "text", 1, userTest, LocalTime.now(), LocalDate.of(2022, Month.JULY, 9) );

        notificationController.addNotification(notification);


        ArgumentCaptor<Notification> argumentCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationRepository).save(argumentCaptor.capture());

        Notification notification1 = argumentCaptor.getValue();
        assertThat(notification1).isEqualTo(notification);
    }

    @Test
    void canFindAllNotification(){


        notificationController.getAllNotifications();
        verify(notificationRepository).findAll();
    }

    @Test
    void canFindNotificationByUser() throws Exception{
/*
        Notification mockNotification = new Notification();
        User user = new User();
        mockNotification.setUser(user);
        lenient().when(notificationRepository.existsById(user.getId())).thenReturn(true);
        lenient().when(notificationRepository.findById(user.getId())).thenReturn(Optional.of(mockNotification));


        ResponseEntity<List<Notification>> notification = notificationController.getNotificationByUser(user.getId());
        assertEquals(mockNotification,notification);*/
    }


}