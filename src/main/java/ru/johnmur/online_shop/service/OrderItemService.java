package ru.johnmur.online_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.johnmur.online_shop.model.OrderItem;
import ru.johnmur.online_shop.repos.OrderItemRepo;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepo orderItemRepo;

    @Autowired
    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    public List<OrderItem> getItemsByOrderId(Long orderId) {
        return orderItemRepo.findByOrderId(orderId);
    }

    public Optional<OrderItem> getItemById(Long orderItemId) {
        return orderItemRepo.findById(orderItemId);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        orderItemRepo.deleteById(orderItemId);
    }
}
