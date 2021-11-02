package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.dao.NotificationRepository;
import com.vkrylov.springboottimetable.entity.Notification;
import com.vkrylov.springboottimetable.exception.AppException;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/notifications/{id}")
    @Transactional
    @PreAuthorize("hasAuthority('notification:post')")
    public Notification updateNotification(@PathVariable int id, @RequestBody Notification notification) {
        Optional<Notification> obj = notificationRepository.findById(id);
        Notification updatedNotification = obj.orElseThrow(
                () -> new AppException("Notification with id = " + id + " not found"));
        updatedNotification.setType(notification.getType());
        return updatedNotification;
    }
}
