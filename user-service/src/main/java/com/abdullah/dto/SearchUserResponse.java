package com.abdullah.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SearchUserResponse(
        String fullName,
        String email,
        String city,
        LocalDateTime createdAt
) {
}
