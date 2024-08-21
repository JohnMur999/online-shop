package ru.johnmur.online_shop.controllers.rest.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.johnmur.online_shop.model.Shop;
import ru.johnmur.online_shop.service.ShopService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopRestController {
    private static final Logger logger = LoggerFactory.getLogger(ShopRestController.class);

    private final ShopService shopService;

    @Autowired
    public ShopRestController(ShopService shopService) {
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

    @GetMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Shop shop = shopService.getShopById(id).orElse(null);
        if (shop == null) {
            logger.error("Shop not found while uploading image");
            return ResponseEntity.notFound().build();
        }

        String folder = "src/main/resources/img/";
        try {
            Path uploadPath = Paths.get(folder);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = uploadPath.resolve(filename);

            Files.write(path, file.getBytes());

            shopService.updateImagePath(id, "/img/" + filename);

            return ResponseEntity.ok(path.toAbsolutePath().toString());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file");
        }
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
