package com.abdullah.dto;

import lombok.Builder;

@Builder
public record UserKafkaPayload(
        String userId,
        String fullName,
        String email,
        String location
) {
}
