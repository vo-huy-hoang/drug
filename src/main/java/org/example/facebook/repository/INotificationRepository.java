package org.example.facebook.repository;

import org.example.facebook.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserId (@Param("userId") Integer userId);
    @Query (value = "from Notification as n join User as u on n.user.id = u.id where u.username = :username OR u.email = :username")
    List<Notification> getNotifications(@Param("username") String username);
}
