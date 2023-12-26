package com.abdullah.dto;

import lombok.Builder;

@Builder
public record WalletDto(
        Long id,
        Double amount,
        String fullName,
        String emailAddress
        ) {
}
