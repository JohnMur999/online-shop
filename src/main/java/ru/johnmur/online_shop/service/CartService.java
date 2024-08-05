package ru.johnmur.online_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.johnmur.online_shop.model.Cart;
import ru.johnmur.online_shop.repos.CartRepo;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Optional<Cart> getCartByUserId(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public Cart createCart(Cart cart) {
        return cartRepo.save(cart);
    }

    public void deleteCart(Cart cart) {
        cartRepo.delete(cart);
    }
}
