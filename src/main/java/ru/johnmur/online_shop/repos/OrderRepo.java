package ru.johnmur.online_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnmur.online_shop.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
