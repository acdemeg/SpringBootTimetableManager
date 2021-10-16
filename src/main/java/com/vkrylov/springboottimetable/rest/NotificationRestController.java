package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.dao.NotificationRepository;
import com.vkrylov.springboottimetable.entity.Notification;
import com.vkrylov.springboottimetable.exception.AppException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NotificationRestController {

    final NotificationRepository notificationRepository;

    public NotificationRestController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/users/{id}/notifications")
    public List<Notification> getNotificationsByUserId(@PathVariable int id){
        return notificationRepository.findAllByUserId(id);
    }

    @PatchMapping("/notifications/{id}")
    @Transactional
    public Notification updateOrder(@PathVariable int id, @RequestBody Notification notification) {
        Optional<Notification> obj = notificationRepository.findById(id);
        Notification updatedNotification = obj.orElseThrow(
                () -> new AppException("Notification with id = " + id + " not found"));
        updatedNotification.setType(notification.getType());
        return updatedNotification;
    }
}
