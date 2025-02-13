package com.example.First_sprint_boot.repository;

import com.example.First_sprint_boot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    Optional<Book> findBooksByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
    Boolean existsByTitle(String title);

}
