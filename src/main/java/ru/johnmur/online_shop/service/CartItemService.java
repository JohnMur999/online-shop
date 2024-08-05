package ru.johnmur.online_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.johnmur.online_shop.model.Cart;
import ru.johnmur.online_shop.model.CartItem;
import ru.johnmur.online_shop.repos.CartItemRepo;
import ru.johnmur.online_shop.repos.CartRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepo cartItemRepo;

    @Autowired
    public CartItemService(CartItemRepo cartItemRepo) {
        this.cartItemRepo = cartItemRepo;
    }

    public List<CartItem> getItemsByCart(Long cartId) {
        return cartItemRepo.findByCartId(cartId);
    }

    public Optional<CartItem> getCartItemByCartId(Long cartItemId) {
        return cartItemRepo.findById(cartItemId);
    }

    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepo.save(cartItem);
    }

    public void deleteCartItem(CartItem cartItem) {
        cartItemRepo.delete(cartItem);
    }


}
