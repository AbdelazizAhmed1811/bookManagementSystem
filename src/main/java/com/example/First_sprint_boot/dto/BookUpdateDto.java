package com.example.First_sprint_boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookUpdateDto {
    private String title;
    private String author;
    private String genre;
    private Boolean available;
}
