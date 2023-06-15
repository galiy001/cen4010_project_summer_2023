package com.geektext.geektext_backend_api.controller;
import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/{id}")
    public BookEntity getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public void addBook(@RequestBody BookEntity bookEntity) {
        bookService.addBook(bookEntity);
    }

    @PutMapping(path = "/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody BookEntity bookEntity) {
        bookService.updateBook(id, bookEntity);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping(path = "/genre/{genre}")
    public List<BookEntity> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }

    @GetMapping(path = "/top-sellers")
    public List<BookEntity> getTopSellingBooks() {
        return bookService.getTopSellingBooks();
    }

    @GetMapping(path = "/rating/{rating}")
    public List<BookEntity> getBooksByRating(@PathVariable double rating) {
        return bookService.getBooksByRating(rating);
    }

    @PutMapping("/discount")
    public void discountBooksByPublisher(@RequestParam("discountPercent") double discountPercent, @RequestParam("publisher") String publisher) {
        bookService.discountBooksByPublisher(discountPercent, publisher);
    }
}
