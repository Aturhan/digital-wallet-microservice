package com.abdullah.dto;

import lombok.Builder;

@Builder
public record BudgetDto(
        String category,
        String description,
        String fullName,
        Double budgetAmount
) {
}
