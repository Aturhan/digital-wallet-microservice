package com.abdullah.dto;

public record AddAmountRequest(
        Long walletId,
        Double newAmount
) {
}
