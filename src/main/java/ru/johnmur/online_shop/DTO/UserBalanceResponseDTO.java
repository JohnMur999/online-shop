package ru.johnmur.online_shop.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class UserBalanceResponseDTO {
    @NotNull(message = "User ID cannot be null")
    Long id;

    @NotNull(message = "User ID cannot be null")
    BigDecimal newBalance;
}
