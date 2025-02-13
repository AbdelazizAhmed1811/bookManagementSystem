//package com.example.First_sprint_boot.controller;
//
//
//import com.example.First_sprint_boot.dto.SignInDto;
//import com.example.First_sprint_boot.dto.AuthenticationResponse;
//import com.example.First_sprint_boot.dto.SignUpDto;
//import com.example.First_sprint_boot.service.AuthenticationService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService service;
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> register(@Valid @RequestBody SignUpDto request, BindingResult result) {
//        if (result.hasErrors()) {
//            // Collect all validation error messages
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error : result.getFieldErrors()) {
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            return ResponseEntity.badRequest().body(errors); // Return error messages as JSON
//        }
//
//        AuthenticationResponse response = service.register(request);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticate(
//           @Valid @RequestBody SignInDto request, BindingResult result
//    ) {
//        if (result.hasErrors()) {
//            // Collect all validation error messages
//            Map<String, String> errors = new HashMap<>();
//            for (FieldError error : result.getFieldErrors()) {
//                errors.put(error.getField(), error.getDefaultMessage());
//            }
//            return ResponseEntity.badRequest().body(errors); // Return error messages as JSON
//        }
//
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//
//    @PostMapping("/refresh-token")
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        service.refreshToken(request, response);
//    }
//
//
//}