package com.example.picallti.service;

import com.example.picallti.model.Notification;
import com.example.picallti.model.User;
import com.example.picallti.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    public NotificationService(){};

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public Notification save(Notification notification){
        try {
            return notificationRepository.save(notification);
        }catch(Exception e){
            logger.error("Exception occur while saving notification",e);
            return null;
        }
    }

    public Optional<Notification> findByUser(User user){
        try {
            return notificationRepository.findByUser(user);
        }catch(Exception e){
            logger.error("Exception occur while saving notification",e);
            return null;
        }
    }

    public List<Notification> findByUser(User user, Integer limit){
        try{
            return notificationRepository.userNotification(user.getId(), PageRequest.of(0, limit));
        }catch (Exception e) {
            logger.error("Exception occur while fetch Notification by User ",e);
            return null;
        }
    }

    public Notification createNotificationObject(String message,User user){
        return new Notification(message,LocalTime.now(),LocalDate.now(), user);
    }

    public Notification findByUserAndNotificationId(User user,Integer id){
        try{
            return notificationRepository.findByUserAndNotificationId(user,id);
        }catch (Exception e) {
            logger.error("Exception occur while fetch Notification by User and Notification Id ",e);
            return null;
        }
    }

}
