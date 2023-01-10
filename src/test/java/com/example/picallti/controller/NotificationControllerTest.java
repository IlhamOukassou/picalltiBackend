package com.example.picallti.controller;

import com.example.picallti.model.Favoris;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class NotificationControllerTest {
    @Mock
    private NotificationService notificationService;
    @Autowired
    private NotificationController notificationController;

    @BeforeEach
    void setUp(){
         notificationController = new NotificationController(notificationService);
    }
    @Test
    void getAllNotifications(){
        Notification notif1 = new Notification();
        Notification notif2 = new Notification();
        Notification notif3 = new Notification();
        List<Notification> mockNotif = new ArrayList<>();
        mockNotif.addAll(List.of(notif1,notif2,notif3));
        ResponseEntity<List<Notification>> mockNotifEntity = new ResponseEntity<>(mockNotif, HttpStatus.OK);
        when(notificationService.findAllNotification()).thenReturn(mockNotif);
        ResponseEntity<List<Notification>> notifs = notificationController.getAllNotifications();
        assertEquals(mockNotifEntity,notifs);
        verify(notificationService).findAllNotification();
    }
    @Test
    void addNotification(){
        Notification mockNotif = new Notification();
        ResponseEntity<Notification> mockNotifEntity = new ResponseEntity<>(mockNotif,HttpStatus.CREATED);
        when(notificationService.addNotification(mockNotif)).thenReturn(mockNotif);
        ResponseEntity<Notification> notif = notificationController.addNotification(mockNotif);
        assertEquals(mockNotifEntity,notif);
        verify(notificationService).addNotification(mockNotif);
    }

    @Test
    void findNotificationByUser(){
        User user = new User();
        Notification notif1 = new Notification("title","text","time","date",user);
        Notification notif2 = new Notification("title2","text2","time2","date2",user);
        List<Notification> mockNotifs = new ArrayList<>();
        ResponseEntity<List<Notification>> mockNotifsEntity = new ResponseEntity<>(mockNotifs,HttpStatus.OK);
        when(notificationService.findByUser(user.getId())).thenReturn(mockNotifs);
        ResponseEntity<List<Notification>> notifs = notificationController.getNotificationByUser(user.getId());
        assertEquals(mockNotifsEntity,notifs);
        verify(notificationService).findByUser(user.getId());
    }


}