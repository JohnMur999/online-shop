package ru.johnmur.online_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.johnmur.online_shop.model.Notification;
import ru.johnmur.online_shop.repos.NotificationRepo;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepo notificationRepo;

    @Autowired
    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepo.findById(id);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepo.save(notification);
    }
    public void deleteNotification(Long id) {
        notificationRepo.deleteById(id);
    }
}
