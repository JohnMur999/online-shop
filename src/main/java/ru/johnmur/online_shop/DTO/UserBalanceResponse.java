package ru.johnmur.online_shop.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserBalanceResponse {
    private Long id;
    private BigDecimal newBalance;

    public UserBalanceResponse(Long id, BigDecimal newBalance) {
        this.id = id;
        this.newBalance = newBalance;
    }
}
