package com.Javacode.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class WalletResponse {

    private UUID walletId;

    private BigDecimal balance;

    private String message;
}
