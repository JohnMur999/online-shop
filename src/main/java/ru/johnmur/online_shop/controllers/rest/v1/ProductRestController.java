package ru.johnmur.online_shop.controllers.rest.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.johnmur.online_shop.model.Product;
import ru.johnmur.online_shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("shops/{name}/products")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductsByShopName(@PathVariable String name) {
        List<Product> productList = productService.getAllProductsByShopName(name);
        if (!productList.isEmpty()) {
            return ResponseEntity.ok(productList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
