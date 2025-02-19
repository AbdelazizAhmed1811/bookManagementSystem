package com.example.First_sprint_boot.controller;

import com.example.First_sprint_boot.dto.AuthenticationResponse;
import com.example.First_sprint_boot.dto.RefreshTokenDto;
import com.example.First_sprint_boot.dto.SignInDto;
import com.example.First_sprint_boot.dto.SignUpDto;
import com.example.First_sprint_boot.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity register(
            @Valid @RequestBody SignUpDto signUpDto, BindingResult result
    ) {
        return ValidateDto(result).map(stringStringMap -> ResponseEntity.badRequest().body(stringStringMap))
                .orElseGet(() -> authService.register(signUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody SignInDto signinDto, BindingResult result
    ) {
       ValidateDto(result).map(stringStringMap -> ResponseEntity.badRequest().body(stringStringMap));

       return authService.authenticate(signinDto);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(
            @Valid @RequestBody RefreshTokenDto refreshTokenDto,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        return ValidateDto(result).map(stringStringMap -> ResponseEntity.badRequest().body(stringStringMap))
                .orElseGet(() -> authService.refreshToken(refreshTokenDto, request, response));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("No token provided!");
        }

        String token = authHeader.substring(7);
        authService.logout(token, request, response);

        return ResponseEntity.ok("Logged out successfully!");
    }


    private Optional<Map<String, String>> ValidateDto(BindingResult result) {
        if (!result.hasErrors()) {
            return Optional.empty();
        }
        // Collect all validation error messages
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return Optional.of(errors);
    }
}