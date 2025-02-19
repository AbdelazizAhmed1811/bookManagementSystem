package com.example.First_sprint_boot.service;

import com.example.First_sprint_boot.dto.AuthenticationResponse;
import com.example.First_sprint_boot.dto.RefreshTokenDto;
import com.example.First_sprint_boot.dto.SignInDto;
import com.example.First_sprint_boot.dto.SignUpDto;
import com.example.First_sprint_boot.entity.Role;
import com.example.First_sprint_boot.entity.Token;
import com.example.First_sprint_boot.entity.User;
import com.example.First_sprint_boot.repository.TokenRepository;
import com.example.First_sprint_boot.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;


    public ResponseEntity register(SignUpDto request) {

        // check if user already exist. if exist than authenticate the user
        if (repository.findByUsername(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email is already in use"));
        }

        User user = new User();
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setRole(Role.USER);

        user = repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "User created successfully. Please sign in.",
                        "userId", user.getId()
                )
        );

    }

    public ResponseEntity<AuthenticationResponse> authenticate(SignInDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getEmail()).orElseThrow();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserNewTokenAndRefreshToken(accessToken, refreshToken, user);

        return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));

    }


    public ResponseEntity refreshToken(
            RefreshTokenDto refreshTokenDto,
            HttpServletRequest request,
            HttpServletResponse response) {

        String refreshToken = refreshTokenDto.getRefreshToken();
        // extract username from refreshToken
        String username = jwtService.extractUsername(refreshToken);

        // check if the user exist in database
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));

        // check if the refreshToken is not valid
        if (!jwtService.isValidRefreshToken(refreshToken, user)) {
            // generate access refreshToken
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        String accessToken = jwtService.generateAccessToken(user);

        saveUserNewToken(accessToken, refreshToken);

        return new ResponseEntity(new AuthenticationResponse(accessToken, refreshToken), HttpStatus.OK);

    }

    public void logout(String token, HttpServletRequest request, HttpServletResponse response) {
        // Check if the token exists in the database
        Token storedToken = tokenRepository.findByAccessToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        // Delete the token from the database to invalidate it
        tokenRepository.delete(storedToken);

        // Clear security context to fully log out the user
        SecurityContextHolder.clearContext();
    }



    private void saveUserNewTokenAndRefreshToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setUser(user);
        tokenRepository.save(token);
    }


    private  void saveUserNewToken (String accessToken, String refreshToken) {
        Token token = tokenRepository
                .findByRefreshToken(refreshToken).orElseThrow();
        token.setAccessToken(accessToken);
        tokenRepository.save(token);
    }
}
