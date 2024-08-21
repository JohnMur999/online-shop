package ru.johnmur.online_shop.controllers.rest.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.johnmur.online_shop.DTO.BalanceUpdateRequest;
import ru.johnmur.online_shop.DTO.UserBalanceResponse;
import ru.johnmur.online_shop.controllers.rest.versionconfigs.WebRestConfigV1;
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
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ResponseEntity.ok(user.getBalance());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserBalanceResponse> updateUserBalance(@PathVariable Long id, @RequestBody BalanceUpdateRequest request) {
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();
        BigDecimal amount = request.getAmount();

        if (amount == null) {
            return ResponseEntity.badRequest().body(new UserBalanceResponse(user.getId(),user.getBalance()));
        }

        BigDecimal newBalance = userService.updateUserBalance(user,amount);

        return ResponseEntity.ok(new UserBalanceResponse(user.getId(), newBalance));
    }
}
