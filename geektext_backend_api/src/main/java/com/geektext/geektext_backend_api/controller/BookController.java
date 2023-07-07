package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.PublisherEntity;
import com.geektext.geektext_backend_api.entity.RatingsEntity;
import com.geektext.geektext_backend_api.entity.CommentsEntity;
import com.geektext.geektext_backend_api.repository.BookRepository;
import com.geektext.geektext_backend_api.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public BookEntity getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping("/{isbn}/comments")
    public List<CommentsEntity> getCommentsForBook(@PathVariable String isbn) {
        return bookService.getCommentsForBook(isbn);
    }

    @PostMapping("/{isbn}/rate")
    public void rateBook(@PathVariable String isbn, @RequestParam Long userId, @RequestParam Long rating) {
        bookService.rateBook(isbn, userId, rating);
    }

    @PostMapping("/{isbn}/comment")
    public void commentBook(@PathVariable String isbn, @RequestParam Long userId, @RequestParam String comment) {
        bookService.commentBook(isbn, userId, comment);
    }

    @GetMapping("/{isbn}/rating")
    public double getAverageRatingForBook(@PathVariable String isbn) {
        return bookService.getAverageRatingForBook(isbn);
    }

    @PostMapping
    public void addBook(@RequestBody BookEntity bookEntity) {
        bookService.addBook(bookEntity);
    }

    @PutMapping(path = "/{isbn}")
    public void updateBook(@PathVariable String isbn, @RequestBody BookEntity bookEntity) {
        bookService.updateBook(isbn, bookEntity);
    }

    @GetMapping(path = "/genre/{genre}")
    public List<BookEntity> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }

    @GetMapping(path = "/top-sellers")
    public List<BookEntity> getTopSellingBooks() {
        return bookService.getTopSellingBooks();
    }

    @GetMapping("/rating/{rating}")
    public List<BookEntity> getBooksByRatingOrHigher(@PathVariable Long rating) {
        return bookService.findByRatingOrHigher(rating);
    }

    @PutMapping("/discount/{discountPercent}/{publisher_id}")
    public void discountBooksByPublisher(@PathVariable double discountPercent, @PathVariable PublisherEntity publisher) {
        bookService.discountBooksByPublisher(discountPercent, publisher);
    }

    @PostMapping("/books")
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity book) {

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/books/{isbn}/description")
    public ResponseEntity<String> getBookDescription(@PathVariable("isbn") String isbn) {
        String bookDescription = bookService.getBookDescriptionByIsbn(isbn);

        if (bookDescription != null) {

            return ResponseEntity.ok(bookDescription);
        } else {

            return ResponseEntity.notFound().build();
        }
    }


}
