package com.Javacode.test.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class WalletRequest {

    public enum OperationType {
        DEPOSIT, WITHDRAW
    }

    @NotNull(message = "поле id не может быть пустым")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$\n", message = "неверный формат UUID")
    private UUID valletId;

    @NotNull(message = "Тип операции не может быть null")
    @Pattern(regexp = "^(DEPOSIT|WITHDRAW)$", message = "Тип операции может быть только DEPOSIT или WITHDRAW")
    private OperationType operationType;

    @NotNull(message = "Тип операции не может быть null")
    @DecimalMin(value = "0.01", message = "Сумма операции не может быть меньше 0.01")
    private BigDecimal amount;
}
