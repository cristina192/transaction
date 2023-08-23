package com.transaction.transaction.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value,Long comprador_Id ,Long vendedor_Id) {
}
