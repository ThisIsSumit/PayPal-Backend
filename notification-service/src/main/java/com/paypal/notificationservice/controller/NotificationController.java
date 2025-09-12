package com.paypal.notificationservice.controller;

import com.paypal.notificationservice.entity.Notification;
import com.paypal.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @PostMapping
    public Notification sendNotification(@RequestBody Notification notification){
        return notificationService.sendNotification(notification);
    }
    @GetMapping("/{userId}")
    public List<Notification> getNotification(@PathVariable String userId){
        return notificationService.getNotificationByUserId(userId);
    }

}
