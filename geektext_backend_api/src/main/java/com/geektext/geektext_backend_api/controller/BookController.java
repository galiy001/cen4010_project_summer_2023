package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.*;
import com.geektext.geektext_backend_api.repository.PublisherRepository;
import com.geektext.geektext_backend_api.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    private final PublisherRepository publisherRepository;

    @Autowired
    public BookController(BookService bookService, PublisherRepository publisherRepository) {
        this.bookService = bookService;
        this.publisherRepository = publisherRepository;
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

    @PutMapping(path = "/{isbn}")
    public void updateBook(@PathVariable String isbn, @RequestBody BookEntity bookEntity) {
        bookService.updateBook(isbn, bookEntity);
    }

    //this is the method where I retrieve a list of books of the same genre.
    @GetMapping(path = "/genre/{genre}")
    public List<BookEntity> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }

    //method that retrieves the ten most sold books in descending order.
    @GetMapping(path = "/top-sellers")
    public List<BookEntity> getTopSellingBooks() {
        return bookService.getTopSellingBooks();
    }

    // method retrieves all the books with a rating equal to or greater than the input rating.
    @GetMapping("/rating/{rating}")
    public List<BookEntity> getBooksByRatingOrHigher(@PathVariable Long rating) {
        return bookService.findByRatingOrHigher(rating);
    }

    // method applies a discount to books associated with a specific publisher.
    @PutMapping("/discount/{discount_percent}/{publisher_id}")
    public ResponseEntity<?> discountBooksByPublisher(@PathVariable("discount_percent") Double discountPercent, @PathVariable("publisher_id") Long publisherId) {
        PublisherEntity publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new RuntimeException("Publisher not found."));

        bookService.discountBooksByPublisher(discountPercent, publisher);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void addBook(@RequestBody BookEntity bookEntity) { //Add book method for adding a book to the database
        bookService.addBook(bookEntity);
    }

    @GetMapping("/{isbn}/description")
    public ResponseEntity<String> getBookDescription(@PathVariable("isbn") String isbn) {  //Method for getting  all book details
        String bookDescription = bookService.getBookDescriptionByIsbn(isbn);

        if (bookDescription != null) {

            return ResponseEntity.ok(bookDescription);
        } else {

            return ResponseEntity.notFound().build();
        }
    }


}
