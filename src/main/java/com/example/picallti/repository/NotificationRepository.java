package com.example.picallti.repository;

import com.example.picallti.model.Notification;
import com.example.picallti.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    Optional<Notification> findByUser(User user);

    @Query("select n from Notification n where n.user.id=:id ORDER BY n.time DESC")
    List<Notification> userNotification(@Param("id") Integer id, Pageable pageSize);

    Notification findByUserAndNotificationId(User user,Integer id);
}
