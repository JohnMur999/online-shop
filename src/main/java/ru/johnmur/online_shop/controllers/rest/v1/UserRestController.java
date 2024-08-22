package ru.johnmur.online_shop.controllers.rest.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.johnmur.online_shop.service.UserService;
import ru.johnmur.online_shop.model.User;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserRestController{
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        if(userService.findById(id).isPresent()) {
           ResponseEntity.status(200).build();
        }
        return ResponseEntity.ok().build();
    }
}
