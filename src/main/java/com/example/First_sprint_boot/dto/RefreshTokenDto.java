package com.example.First_sprint_boot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {

    @NotBlank(message = "Refresh token is required")
    private String refreshToken;
}
