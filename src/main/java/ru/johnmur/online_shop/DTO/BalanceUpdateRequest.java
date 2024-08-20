package ru.johnmur.online_shop.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BalanceUpdateRequest {
    private Long id;
    private BigDecimal amount;
}
