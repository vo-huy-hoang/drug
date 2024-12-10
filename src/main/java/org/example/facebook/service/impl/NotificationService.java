package org.example.facebook.service.impl;

import org.example.facebook.model.Notification;
import org.example.facebook.model.User;
import org.example.facebook.repository.INotificationRepository;
import org.example.facebook.repository.IUserRepository;
import org.example.facebook.service.INotificationService;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;
    @Autowired
    private IUserRepository userRepository;
    @Override
    public List<Notification> findByUserId(Integer userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public void createNotification(Integer userId, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setContent(content);
        notification.setCreateAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @Override
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public List<Notification> getNotifications(String username) {
        return notificationRepository.getNotifications(username);
    }
}
