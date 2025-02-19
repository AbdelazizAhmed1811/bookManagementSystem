package com.example.First_sprint_boot.entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accessToken;
    @Column(nullable = false, unique = true)
    private String refreshToken;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}