package ru.johnmur.online_shop.controllers.admintools;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.johnmur.online_shop.service.UserService;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/panel")
    public String adminPanel(Model model) {
        return "panel";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/user")
    public String adminUser(Model model) {
        model.addAttribute("users", userService.findAllSortedByUsername());
        return "user";
    }
}
