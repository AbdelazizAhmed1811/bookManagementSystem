package com.example.First_sprint_boot.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    @Email(message = "Invalid Credentials")
    @NotBlank(message = "Email is required")
    @Size(max = 100, message = "Invalid Credentials")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 16, message = "Invalid Credentials ")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Invalid Credentials"
    )
    private String password;
}