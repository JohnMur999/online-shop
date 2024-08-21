package ru.johnmur.online_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.johnmur.online_shop.model.Product;
import ru.johnmur.online_shop.model.Shop;
import ru.johnmur.online_shop.repos.ProductRepo;
import ru.johnmur.online_shop.repos.ShopRepo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ShopRepo shopRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, ShopRepo shopRepo) {
        this.productRepo = productRepo;
        this.shopRepo = shopRepo;
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    public List<Product> getAllProductsByShopName(String name) {
        Optional<Shop> shop = shopRepo.findByName(name);
        if (shop.isPresent()) {
            return productRepo.findByShopId(shop.get().getId());
        } else {
            return Collections.emptyList();
        }

    }

    public boolean createProduct(String name, String desc, BigDecimal price, Integer quantity) {
        if (productRepo.findByName(name) != null) {
            return false;
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);
        product.setQuantity(quantity);
        productRepo.save(product);

        return true;
    }

    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
