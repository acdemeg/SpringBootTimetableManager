package com.vkrylov.springboottimetable.dao;

import com.vkrylov.springboottimetable.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
