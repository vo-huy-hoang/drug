package org.example.facebook.controller;

import org.springframework.security.core.Authentication;
import org.example.facebook.model.Notification;
import org.example.facebook.service.INotificationService;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("*")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;
    @Autowired
    private IUserService userService;
    @GetMapping("")
    public ResponseEntity<List<Notification>> getNotifications(Authentication authentication) {
        List<Notification> notificationList = notificationService.getNotifications(authentication.getName());
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }
}
