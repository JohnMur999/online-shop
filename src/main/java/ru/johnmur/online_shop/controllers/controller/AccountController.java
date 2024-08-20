package ru.johnmur.online_shop.controllers.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.johnmur.online_shop.model.User;
import ru.johnmur.online_shop.service.UserDetailsServiceImpl;
import ru.johnmur.online_shop.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public AccountController(UserService userService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userService = userService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @GetMapping
    public String account(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userService.findByUsername(username);

        model.addAttribute("user", user);
        return "account";
    }
}
