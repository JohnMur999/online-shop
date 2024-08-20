package ru.johnmur.online_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnmur.online_shop.model.Shop;

import java.util.Optional;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Long> {
    Optional<Shop> findByName(String name);
}
