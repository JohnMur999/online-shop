package ru.johnmur.online_shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.johnmur.online_shop.controllers.rest.v1.ShopRestController;
import ru.johnmur.online_shop.model.Shop;
import ru.johnmur.online_shop.repos.ShopRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    private static final Logger logger = LoggerFactory.getLogger(ShopRestController.class);
    private final ShopRepo shopRepo;

    @Autowired
    public ShopService(ShopRepo shopRepo) {
        this.shopRepo = shopRepo;
    }

    public List<Shop> findAllShops() {
        return shopRepo.findAll();
    }

    public Optional<Shop> getShopById(Long id) {
        return shopRepo.findById(id);
    }

    public boolean createShop(Shop shop) {
        if (shopRepo.findByName(shop.getName()).isPresent()) {
            logger.error("Error while creating shop", shop.getName());
            return false;
        }
        if (shop.getImagePath().isEmpty()) {
            shop.setImagePath("/img/default.png");
        }
        shopRepo.save(shop);
        logger.info("Successfully created shop {}", shop.getName());
        return true;
    }

    public Optional<Shop> getShopByName(String name) {
        return shopRepo.findByName(name);
    }


    public ResponseEntity<Boolean> updateImagePath(Long id, String imagePath) {
        if (shopRepo.findById(id).isPresent()) {
            Shop shop = shopRepo.findById(id).get();
            shop.setImagePath(imagePath);
            shopRepo.save(shop);
            logger.info("Successfully updated image path {}", imagePath);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    public Shop updateShop(Shop shop) {
        return shopRepo.save(shop);
    }

    public boolean deleteShopById(Long id) {
        if (shopRepo.existsById(id)) {
            shopRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
