package ru.johnmur.online_shop.controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.johnmur.online_shop.model.User;
import ru.johnmur.online_shop.service.ShopService;
import org.springframework.ui.Model;
import ru.johnmur.online_shop.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ShopService shopService;
    private final UserService userService;

    @Autowired
    public HomeController(ShopService shopService, UserService userService) {
        this.shopService = shopService;
        this.userService = userService;
    }

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("shops", shopService.findAllShops());

        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        return "home";
    }

}
