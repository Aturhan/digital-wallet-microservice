package com.abdullah.dto;

import lombok.Builder;

@Builder
public record AuthRequest(
        String email,
        String password
) {
}
