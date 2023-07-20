package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.*;
import com.geektext.geektext_backend_api.repository.PublisherRepository;
import com.geektext.geektext_backend_api.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;

    private final PublisherRepository publisherRepository;

    static class RateBookRequest {
        private Long userId;
        private Long rating;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getRating() {
            return rating;
        }

        public void setRating(Long rating) {
            this.rating = rating;
        }
    }

    static class CommentBookRequest {
        private Long userId;
        private String comment;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

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
    public ResponseEntity<String> rateBook(@PathVariable String isbn, @RequestBody RateBookRequest request, RatingsEntity datestamp) {
        bookService.rateBook(isbn, request.getUserId(), request.getRating());
        Date currentTimestamp = new Date();
        String responseMessage = "Comment successfully recorded for User ID: " + request.getUserId() + " and datestamp: " + currentTimestamp;
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @PostMapping("/{isbn}/comment")
    public ResponseEntity<String> commentBook(@PathVariable String isbn, @RequestBody CommentBookRequest request) {
        bookService.commentBook(isbn, request.getUserId(), request.getComment());
        Date currentTimestamp = new Date();
        String responseMessage = "Comment successfully recorded for User ID: " + request.getUserId() + " and datestamp: " + currentTimestamp;
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/{isbn}/rating")
    public double getAverageRatingForBook(@PathVariable String isbn) {
        return bookService.getAverageRatingForBook(isbn);
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

    @PutMapping("/discount/{discount_percent}/{publisher_id}")
    public ResponseEntity<?> discountBooksByPublisher(@PathVariable("discount_percent") Double discountPercent, @PathVariable("publisher_id") Long publisherId) {
        PublisherEntity publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new RuntimeException("Publisher not found."));

        bookService.discountBooksByPublisher(discountPercent, publisher);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public void addBook(@RequestBody BookEntity bookEntity) {
        bookService.addBook(bookEntity);
    }

    @GetMapping("/{isbn}/description")
    public ResponseEntity<String> getBookDescription(@PathVariable("isbn") String isbn) {
        String bookDescription = bookService.getBookDescriptionByIsbn(isbn);

        if (bookDescription != null) {

            return ResponseEntity.ok(bookDescription);
        } else {

            return ResponseEntity.notFound().build();
        }
    }


}
