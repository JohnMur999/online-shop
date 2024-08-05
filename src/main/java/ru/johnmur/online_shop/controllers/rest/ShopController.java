package ru.johnmur.online_shop.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.johnmur.online_shop.model.Shop;
import ru.johnmur.online_shop.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/all")
    public List<Shop> getAll() {
        return shopService.findAllShops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getById(@PathVariable Long id) {
        return shopService.getShopById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Shop> addShop(@RequestBody Shop shop) {
        if (shop.getName().isEmpty() || shop.getDescription().isEmpty()) {
            logger.error("Shop name or description is empty", shop);
            return ResponseEntity.badRequest().build();
        } else if (shopService.createShop(shop)) {
            logger.info("Shop created");
            return ResponseEntity.ok().build();
        } else {
            logger.error("Shop already exists");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Shop> deleteShop(@PathVariable Long id) {
        if (shopService.deleteShopById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
