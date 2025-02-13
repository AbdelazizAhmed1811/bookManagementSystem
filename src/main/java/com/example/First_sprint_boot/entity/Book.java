package com.example.First_sprint_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, nullable = false)
    private String title;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String author;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String genre;
    @NotNull
    private boolean available;
}

