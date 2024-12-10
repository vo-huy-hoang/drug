package org.example.facebook.service;

import org.example.facebook.dto.NotificationCreateDTO;
import org.example.facebook.model.Notification;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INotificationService {
    List<Notification> findByUserId (Integer userId);
    void createNotification(Integer userId, String content);
    void delete(Notification notification);
    List<Notification> getNotifications(String username);
}
