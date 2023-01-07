package com.example.picallti.service;

import com.example.picallti.model.Notification;
import com.example.picallti.model.User;
import com.example.picallti.repository.NotificationRepository;
import com.example.picallti.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    public NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;
    public NotificationService(){}

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public Notification addNotification(Notification notification){
        try{
            return notificationRepository.save(notification);
        }catch (Exception e) {
            logger.error("Exception occur while save Notification ",e);
            return null;
        }
    }
    public List<Notification> findAllNotification(){
        try{
            return notificationRepository.findAll();
        }catch (Exception e) {
            logger.error("Exception occur while fetching Notification ",e);
            return null;
        }
    }

    public List<Notification> findByUser(int id){
        try {
            User user = userRepository.findById(id).get();
            return notificationRepository.findByUser(user);
        }catch (Exception e){
            logger.error("Exception occur while fetching Notification ",e);
            return null;
        }
    }

}
