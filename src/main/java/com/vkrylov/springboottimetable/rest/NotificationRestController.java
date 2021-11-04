package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.entities.Notification;
import com.vkrylov.springboottimetable.exceptions.AppException;
import com.vkrylov.springboottimetable.repositories.NotificationRepository;
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
    @PreAuthorize("hasAuthority('ADMIN') or @authComponent.hasPermission(authentication, #id)")
    public List<Notification> getNotificationsByUserId(@PathVariable int id){
        return notificationRepository.findAllByUserId(id);
    }

    @PostMapping("/notifications/{id}")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    public Notification updateNotification(@PathVariable int id, @RequestBody Notification notification) {
        Optional<Notification> obj = notificationRepository.findById(id);
        Notification updatedNotification = obj.orElseThrow(
                () -> new AppException("Notification with id = " + id + " not found"));
        updatedNotification.setType(notification.getType());
        return updatedNotification;
    }
}
