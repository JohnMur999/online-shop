package ru.johnmur.online_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.johnmur.online_shop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByShopId(Long shopId);
}
