package com.geektext.geektext_backend_api.service.implementation;

import com.geektext.geektext_backend_api.entity.*;
import com.geektext.geektext_backend_api.repository.BookRepository;
import com.geektext.geektext_backend_api.repository.CommentRepository;
import com.geektext.geektext_backend_api.repository.RatingRepository;
import com.geektext.geektext_backend_api.repository.UserRepository;
import com.geektext.geektext_backend_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
/*
This class implements the BookService interface, providing implementations for all its methods.
It uses the CommentRepository and RatingRepository to interact with the database for comment and rating related operations.
*/
@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public BookServiceImplementation(BookRepository bookRepository, UserRepository userRepository, CommentRepository commentRepository, RatingRepository ratingRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
    }

    public BookEntity getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }
// Handles GET requests to fetch all comments for a book
    public List<CommentsEntity> getCommentsForBook(String isbn) {
        Optional<BookEntity> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isEmpty()) {
            throw new IllegalArgumentException("Book not found.");
        }
        BookEntity book = bookOptional.get();
        return commentRepository.findByBookIsbn(book.getIsbn());
    }

    // Handles POST requests to add a new rating for a book by a user

    public void rateBook(String isbn, Long userId, Long rating) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            BookEntity book = getBookByIsbn(isbn);
            if (book != null) {
                if (rating < 1 || rating > 5) {
                    throw new IllegalArgumentException("Rating must be between 1 and 5.");
                }
                RatingsEntity newRating = new RatingsEntity();
                newRating.setUser(user);
                newRating.setBook(book);
                newRating.setRating(rating);
                newRating.setDatestamp(LocalDateTime.now());
                ratingRepository.save(newRating);
            } else {
                throw new IllegalArgumentException("Book not found.");
            }
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }
// Handles POST requests to add a new comment for a book by a user
    public void commentBook(String isbn, Long userId, String comment) {
        BookEntity book = getBookByIsbn(isbn);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));


        if (book != null) {
            CommentsEntity newComment = new CommentsEntity(comment, user, book);
            newComment.setDatestamp(LocalDateTime.now());
            commentRepository.save(newComment);
        } else {
            throw new IllegalArgumentException("Book not found.");
        }
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public void updateBook(String isbn, BookEntity book) {
        Optional<BookEntity> optionalEntity = bookRepository.findByIsbn(isbn);
        if (optionalEntity.isPresent()) {
            BookEntity existingEntity = optionalEntity.get();
            // Assuming all fields should be updated. Adjust as needed.
            existingEntity.setName(book.getName());
            existingEntity.setAuthor(book.getAuthor());
            existingEntity.setGenre(book.getGenre());
            existingEntity.setPublisher(book.getPublisher());
            existingEntity.setIsbn(book.getIsbn());
            bookRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Book with ISBN " + isbn + " not found.");
        }
    }
// Handles GET requests to fetch the average rating for a book
    public Double getAverageRatingForBook(String isbn) {
        Double avg = ratingRepository.findAverageRatingByBookId(isbn);
        if (avg == null) {
            throw new IllegalArgumentException("No ratings found for this book");
        }
        return avg;
    }

    public Optional<BookEntity> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<BookEntity> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<BookEntity> getTopSellingBooks() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "copiesSold"));
        return bookRepository.findTopSellers(pageable);
    }

    @Override
    public void discountBooksByPublisher(Double discountPercent, PublisherEntity publisher) {
        List<BookEntity> books = bookRepository.findByPublisher(publisher);

        BigDecimal discount = BigDecimal.valueOf(discountPercent).divide(BigDecimal.valueOf(100));

        for (BookEntity book : books) {
            BigDecimal currentPrice = BigDecimal.valueOf(book.getPrice());
            BigDecimal discountedPrice = currentPrice.subtract(currentPrice.multiply(discount));
            discountedPrice = discountedPrice.setScale(2, RoundingMode.HALF_UP);

            book.setPrice(discountedPrice.doubleValue());
            bookRepository.save(book);
        }
    }

    public List<BookEntity> findByRatingOrHigher(Long rating) {
        return bookRepository.findByRatingOrHigher(rating);
    }

    @Override
    public String getBookDescriptionByIsbn(String isbn) {
        Optional<BookEntity> book = bookRepository.findByIsbn(isbn);
        if (book.isPresent()) {
            BookEntity bookEntity = book.get();
            return bookEntity.getDescription();
        } else
            return null;
    }
}
