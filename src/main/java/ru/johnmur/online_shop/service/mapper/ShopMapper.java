package ru.johnmur.online_shop.service.mapper;

import org.springframework.stereotype.Component;
import ru.johnmur.online_shop.DTO.ShopDTO;
import ru.johnmur.online_shop.model.Shop;

@Component
public class ShopMapper {
    public ShopDTO toShopDTO(Shop shop) {
        return new ShopDTO(
                shop.getId(),
                shop.getName(),
                shop.getDescription(),
                shop.getImagePath()
        );
    }

    /*public Shop toEntity(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(userDTO.getUserID());
        shop.setUsername(userDTO.getUsername());
        shop.setGender(userDTO.getGender());
        shop.setBalance(userDTO.getBalance());
        shop.setRole(userDTO.getRole());

        return shop;
    }*/
}
