package com.abdullah.dto;

import lombok.Builder;

@Builder
public record ExpenseDto(
        Long id,
        String category,
        String description,
        String fullName,
        Long walletId
) {
}
