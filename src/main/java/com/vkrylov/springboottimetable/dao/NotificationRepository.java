package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByUserId(int id);
}
