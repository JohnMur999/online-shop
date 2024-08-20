package ru.johnmur.online_shop.controllers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.johnmur.online_shop.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registerSubmit(@RequestParam String username, @RequestParam String password,
                                 @RequestParam String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        boolean isRegistered = userService.registerUser(username, password);
        if (isRegistered) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Username already exists");
            return "/register";
        }
    }
}
