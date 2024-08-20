package ru.johnmur.online_shop.controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.johnmur.online_shop.service.ShopService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ShopService shopService;

    @Autowired
    public HomeController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("shops", shopService.findAllShops());
        return "home";
    }

}
