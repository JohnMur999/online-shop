package ru.johnmur.online_shop.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class UserRegistrationDTO {
    @NotNull(message = "User ID cannot be null")
    Long userID;

    @NotNull(message = "User username cannot be null")
    String username;

    @NotNull(message = "User password cannot be null")
    String password;

    @NotNull(message = "User gender cannot be null")
    String gender;

    @NotNull(message = "User balance cannot be null")
    BigDecimal balance;

    @NotNull(message = "User role cannot be null")
    String role;
}
