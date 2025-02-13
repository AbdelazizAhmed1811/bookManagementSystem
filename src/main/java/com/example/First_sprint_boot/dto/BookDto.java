package com.example.First_sprint_boot.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BookDto {
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
    private String title;
    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
    private String author;
    @NotBlank(message = "Genre is required")
    @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
    private String genre;
    @NotBlank
    private boolean available;
}
