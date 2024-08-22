package ru.johnmur.online_shop.controllers.rest.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.johnmur.online_shop.DTO.UserBalanceRequestDTO;
import ru.johnmur.online_shop.DTO.UserBalanceResponseDTO;
import ru.johnmur.online_shop.model.User;
import ru.johnmur.online_shop.service.UserService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/balance")
public class BalanceRestController {
    private final UserService userService;

    public BalanceRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BigDecimal> getUserBalance(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        return ResponseEntity.ok(user.getBalance());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserBalanceResponseDTO> updateUserBalance(@PathVariable Long id, @RequestBody UserBalanceRequestDTO request) {
        Optional<User> optionalUser = userService.findById(id);

        BigDecimal amount = request.getAmount();

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (amount == null) {
            return ResponseEntity.badRequest().body(new UserBalanceResponseDTO(optionalUser.get().getId(), request.getAmount()));
        }

        User user = optionalUser.get();
        BigDecimal newBalance = userService.updateUserBalance(user, amount);

        return ResponseEntity.ok(new UserBalanceResponseDTO(user.getId(), newBalance));
    }
}
