package com.example.First_sprint_boot.dto;
import lombok.*;



@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
