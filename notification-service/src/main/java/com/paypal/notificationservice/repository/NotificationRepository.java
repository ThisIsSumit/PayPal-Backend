package com.paypal.notificationservice.repository;


import com.paypal.notificationservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification,Long> {
    List<Notification> findByUserId(String userId);
}
