package com.vkrylov.springboottimetable.repositories;

import com.vkrylov.springboottimetable.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByUserId(int id);
}
