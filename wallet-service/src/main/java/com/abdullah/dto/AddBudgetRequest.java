package com.abdullah.dto;

import lombok.Builder;

@Builder
public record AddBudgetRequest(
        Long walletId,
        String category,
        String description,
        Double budgetAmount
) {
}
