package com.example.First_sprint_boot.controller;

import com.example.First_sprint_boot.dto.BookDto;
import com.example.First_sprint_boot.dto.BookUpdateDto;
import com.example.First_sprint_boot.entity.Book;
import com.example.First_sprint_boot.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    final BookService bookService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDTO) {
        Optional<Book> book = bookService.createBook(bookDTO);

        if (book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Book already exists!");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book added successfully!");
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();

        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        Optional<Book> book = bookService.getBookById(bookId);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        boolean isDeleted = bookService.deleteBookById(bookId);

        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody BookUpdateDto bookUpdateDto) {

        Optional<Book> updatedBook = bookService.updateBook(bookId, bookUpdateDto);

        return updatedBook
                .map(ResponseEntity::ok) // Return 200 OK with updated book
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }


    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getBooksByGenre(genre);

        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }


    @PatchMapping("/{bookId}/availability")
    public ResponseEntity<Book> updateAvailability(@PathVariable Long bookId, @RequestParam boolean available) {
        Optional<Book> updatedBook = bookService.updateAvailability(bookId, available);

        return updatedBook
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.getBooksByAuthor(author);

        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }


}
