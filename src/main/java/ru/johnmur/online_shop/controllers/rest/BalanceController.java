package ru.johnmur.online_shop.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.johnmur.online_shop.DTO.BalanceUpdateRequest;
import ru.johnmur.online_shop.DTO.UserBalanceResponse;
import ru.johnmur.online_shop.model.User;
import ru.johnmur.online_shop.service.UserService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    private final UserService userService;

    public BalanceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BigDecimal> getUserBalance(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get().getBalance());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<UserBalanceResponse> upUserBalance(@RequestBody BalanceUpdateRequest request) {
        Optional<User> optionalUser = userService.findById(request.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBalance(user.getBalance().add(request.getAmount()));
            userService.save(user);
            UserBalanceResponse response = new UserBalanceResponse(user.getId(), user.getBalance());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
