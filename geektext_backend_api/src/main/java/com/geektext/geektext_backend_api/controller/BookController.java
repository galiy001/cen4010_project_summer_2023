package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.*;
import com.geektext.geektext_backend_api.repository.PublisherRepository;
import com.geektext.geektext_backend_api.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.stream.events.Comment;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    public static class CommentRequest {
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

        private Long userId;
        private String comment;

    }

    public static class RateRequest {
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getRate() {
            return rate;
        }

        public void setRate(Long rate) {
            this.rate = rate;
        }

        private Long userId;
        private Long rate;

    }

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
    public ResponseEntity<String> rateBook(@PathVariable String isbn, @RequestBody RateRequest request) {
        Date dateStamp = new Date();
        Long userId = request.getUserId();
        Long rating = request.getRate();

        bookService.rateBook(isbn, userId, rating);

        String responseMessage = "Rating successfully recorded for book with ISBN: " + isbn +
                ", User ID: " + userId +
                ", Date Stamp: " + dateStamp +
                ", Rating: " + rating;

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @PostMapping("/{isbn}/comment")
    public ResponseEntity<String> commentBook(@PathVariable String isbn, @RequestBody CommentRequest request) {
        Date dateStamp = new Date();
        Long userId = request.getUserId();
        String comment = request.getComment();

        bookService.commentBook(isbn, userId, comment);

        String responseMessage = "Rating successfully recorded for book with ISBN: " + isbn +
                ", User ID: " + userId +
                ", Date Stamp: " + dateStamp +
                ", Comment: " + comment;

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
