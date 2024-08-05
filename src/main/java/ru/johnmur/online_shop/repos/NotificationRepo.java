package ru.johnmur.online_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnmur.online_shop.model.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    Notification findByUserId(Long id);
}
