package com.abdullah.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKafkaPayload {
    private String userId;
    private String fullName;
    private String email;
    private String location;
}
