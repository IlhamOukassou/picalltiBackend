package com.example.picallti.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.picallti.model.Notification;
import com.example.picallti.model.User;
import com.example.picallti.repository.NotificationRepository;
import com.example.picallti.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {NotificationService.class})
@ExtendWith(SpringExtension.class)
class NotificationServiceTest {
    @MockBean
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link NotificationService#addNotification(Notification)}
     */
    @Test
    void testAddNotification() {
        Notification notification = new Notification();
        when(notificationRepository.save((Notification) any())).thenReturn(notification);
        assertSame(notification, notificationService.addNotification(new Notification()));
        verify(notificationRepository).save((Notification) any());
    }

    /**
     * Method under test: {@link NotificationService#findAllNotification()}
     */
    @Test
    void testFindAllNotification() {
        ArrayList<Notification> notificationList = new ArrayList<>();
        when(notificationRepository.findAll()).thenReturn(notificationList);
        List<Notification> actualFindAllNotificationResult = notificationService.findAllNotification();
        assertSame(notificationList, actualFindAllNotificationResult);
        assertTrue(actualFindAllNotificationResult.isEmpty());
        verify(notificationRepository).findAll();
    }

    /**
     * Method under test: {@link NotificationService#findByUser(int)}
     */
    @Test
    void testFindByUser() {
        ArrayList<Notification> notificationList = new ArrayList<>();
        when(notificationRepository.findByUser((User) any())).thenReturn(notificationList);
        when(userRepository.findById((Integer) any()))
                .thenReturn(Optional.of(new User("Nom", "Prenom", "jane.doe@example.org", 1, "Bio", "Image Name")));
        List<Notification> actualFindByUserResult = notificationService.findByUser(1);
        assertSame(notificationList, actualFindByUserResult);
        assertTrue(actualFindByUserResult.isEmpty());
        verify(notificationRepository).findByUser((User) any());
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link NotificationService#findByUser(int)}
     */
    @Test
    void testFindByUser2() {
        when(notificationRepository.findByUser((User) any())).thenReturn(new ArrayList<>());
        when(userRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertNull(notificationService.findByUser(1));
        verify(userRepository).findById((Integer) any());
    }
}

