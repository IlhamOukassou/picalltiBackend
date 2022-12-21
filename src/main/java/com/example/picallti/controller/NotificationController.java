package com.example.picallti.controller;

import com.example.picallti.model.Notification;
import com.example.picallti.model.User;
import com.example.picallti.service.NotificationService;
import com.example.picallti.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    public NotificationController(){};

   @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications(){
       List<Notification> notifications = notificationService.findAllNotification();
       return new ResponseEntity<>(notifications, HttpStatus.OK);
   }

    @GetMapping("/findallbyuser/{id}")
    public ResponseEntity<List<Notification>> getNotificationByUser(@PathVariable Integer id){
        List<Notification> notificationByUser = notificationService.findByUser(id);
        return new ResponseEntity<>(notificationByUser,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Notification> addNotification(@RequestBody Notification notification){
        Notification newNotification = notificationService.addNotification(notification);
        return new ResponseEntity<>(newNotification,HttpStatus.CREATED);
    }
}
