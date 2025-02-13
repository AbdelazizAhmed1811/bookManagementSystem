package com.example.First_sprint_boot.service;

import com.example.First_sprint_boot.dto.BookDto;
import com.example.First_sprint_boot.dto.BookUpdateDto;
import com.example.First_sprint_boot.entity.Book;
import com.example.First_sprint_boot.mapper.BookMapper;
import com.example.First_sprint_boot.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    final BookRepository bookRepository;
    final BookMapper bookMapper;

    public Optional<Book> createBook(BookDto bookDto) {
        // Check if the book already exists
        if (bookRepository.findByTitle(bookDto.getTitle()).isPresent()) {
            return Optional.empty(); // Book already exists
        }

        // Convert DTO to Entity
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .genre(bookDto.getGenre())
                .available(bookDto.isAvailable())
                .build();

        // Save to DB and return it
        return Optional.of(bookRepository.save(book));
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }


    public boolean deleteBookById(Long bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true; // Book deleted successfully
        }
        return false; // Book not found
    }



    public Optional<Book> updateBook(Long bookId, BookUpdateDto bookUpdateDto) {
        Optional<Book> existingBook = bookRepository.findById(bookId);

        if (existingBook.isEmpty()) {
            return Optional.empty(); // Book not found, return empty
        }

        Book book = existingBook.get();

        // Check if title is being updated and already exists in another book
        if (bookUpdateDto.getTitle() != null &&
                !bookUpdateDto.getTitle().equals(book.getTitle()) &&
                bookRepository.existsByTitle(bookUpdateDto.getTitle())) {
            return Optional.empty();
        }

        // Only update fields if they are present in the DTO
        if (bookUpdateDto.getTitle() != null) {
            book.setTitle(bookUpdateDto.getTitle());
        }
        if (bookUpdateDto.getAuthor() != null) {
            book.setAuthor(bookUpdateDto.getAuthor());
        }
        if (bookUpdateDto.getGenre() != null) {
            book.setGenre(bookUpdateDto.getGenre());
        }
        if (bookUpdateDto.getAvailable() != null) {
            book.setAvailable(bookUpdateDto.getAvailable());
        }

        return Optional.of(bookRepository.save(book)); // Save and return updated book
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre); // Assumes a custom query is defined in the repository
    }


    public Optional<Book> updateAvailability(Long bookId, boolean available) {
        Optional<Book> existingBook = bookRepository.findById(bookId);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setAvailable(available); // Update availability
            return Optional.of(bookRepository.save(book)); // Save and return the updated book
        }
        return Optional.empty(); // If book not found, return an empty Optional
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author); // Assumes a custom query is defined in the repository
    }
}
