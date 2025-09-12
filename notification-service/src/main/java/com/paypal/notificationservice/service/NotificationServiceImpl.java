package com.paypal.notificationservice.service;

import com.paypal.notificationservice.entity.Notification;
import com.paypal.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class NotificationServiceImpl  implements  NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public Notification sendNotification(Notification notification) {
        notification.setSentAt((LocalDateTime.now()) );

        return  notificationRepository.save(notification);

    }

    @Override
    public List<Notification> getNotificationByUserId(String userId) {
        return  notificationRepository.findByUserId(userId);
    }
}
