package com.example.First_sprint_boot.mapper;

import com.example.First_sprint_boot.dto.BookDto;
import com.example.First_sprint_boot.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookDto bookDto);
    BookDto toDto(Book book);
}
