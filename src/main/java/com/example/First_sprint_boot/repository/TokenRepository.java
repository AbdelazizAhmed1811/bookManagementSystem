package com.example.First_sprint_boot.repository;

import com.example.First_sprint_boot.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByAccessToken(String token);

    Optional<Token > findByRefreshToken(String token);

    Boolean existsByRefreshToken(String token);

}