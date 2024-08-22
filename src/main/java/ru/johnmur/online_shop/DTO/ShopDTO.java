package ru.johnmur.online_shop.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ShopDTO {
    @NotNull(message = "User ID cannot be null")
    Long id;

    @NotNull(message = "User ID cannot be null")
    String name;

    String description;

    String imagePath;
}
