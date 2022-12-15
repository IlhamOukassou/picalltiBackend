package com.example.picallti.controller;

import com.example.picallti.model.User;
import com.example.picallti.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/notification/")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    private static Logger logger = LoggerFactory.getLogger(NotificationController.class);
    public NotificationController(){};

    /*@RequestMapping(value="/notifications/user",method = RequestMethod.GET)
    public ResponseEntity<Object> getNotificationsByUser(@RequestParam(required = false,defaultValue = "")String limit){
        logger.debug("inside getNotificationsByUser api for fetch user notification");

    }*/
}
