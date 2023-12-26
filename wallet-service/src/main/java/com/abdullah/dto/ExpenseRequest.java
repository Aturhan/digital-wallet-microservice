package com.abdullah.dto;

public record ExpenseRequest(
        Long walletId,
        Double expenseAmount,
        String category,
        String description
) {
}
