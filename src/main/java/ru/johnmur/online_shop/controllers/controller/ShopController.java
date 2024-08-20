package ru.johnmur.online_shop.controllers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.johnmur.online_shop.model.Shop;
import ru.johnmur.online_shop.service.ShopService;

import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{name}")
    public String getShop(@PathVariable String name, Model model) {
        Optional<Shop> optionalShop = shopService.getShopByName(name);
        if (optionalShop.isPresent()) {
            model.addAttribute("shop", optionalShop.get());
            return "shop";
        } else {
            return "error";
        }

    }
}
