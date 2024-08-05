package ru.johnmur.online_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnmur.online_shop.model.Shop;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Long> {
    Shop findByName(String name);
}
