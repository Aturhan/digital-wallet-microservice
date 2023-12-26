package com.abdullah.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateUserRequest(
        @NotBlank(message = "Id should be valid")
        String id,
        @NotBlank(message = "Full name is required'")
        String fullName,
        @Email
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "City is required")
        String city
) {

}
