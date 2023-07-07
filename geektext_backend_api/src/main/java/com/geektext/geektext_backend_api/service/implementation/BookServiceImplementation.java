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
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    public BookEntity getBookByIsbn(String isbn){
        return bookRepository.findById(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }

    public List<CommentsEntity> getCommentsForBook(String isbn) {
        Optional<BookEntity> bookOptional = bookRepository.findByIsbn(isbn);
        if (bookOptional.isEmpty()) {
            throw new IllegalArgumentException("Book not found.");
        }
        BookEntity book = bookOptional.get();
        return commentRepository.findByBookIsbn(book.getIsbn());
    }

    public void rateBook(String isbn, Long userId, Long rating){
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if(((Optional<?>) userOptional).isPresent()){
            UserEntity user = userOptional.get();
            BookEntity book = getBookByIsbn(isbn);
            if(book != null){
                if (rating < 1 || rating > 5) {
                    throw new IllegalArgumentException("Rating must be between 1 and 5.");
                }
                RatingsEntity newRating = new RatingsEntity();
                newRating.setUser(user);
                newRating.setBook(book);
                newRating.setRating(rating);
                ratingRepository.save(newRating);
            } else {
                throw new IllegalArgumentException("Book not found.");
            }
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }

    public void commentBook(String isbn, Long userId, String comment) {
        BookEntity book = getBookByIsbn(isbn);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));


        if (book != null) {
            CommentsEntity newComment = new CommentsEntity(comment, user, book);
            commentRepository.save(newComment);
        } else {
            throw new IllegalArgumentException("Book not found.");
        }
    }
            @Override
            public List<BookEntity> getAllBooks () {
                return bookRepository.findAll();
            }

            @Override
            public Optional<BookEntity> findBookByIsbn (String isbn){
                return bookRepository.findByIsbn(isbn);
            }

            @Override
            public void addBook (BookEntity book){
                bookRepository.save(book);
            }

            @Override
            public void updateBook (String isbn, BookEntity book){
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

            public Double getAverageRatingForBook (String isbn){
                Double avg = ratingRepository.findAverageRatingByBookId(isbn);
                if (avg == null) {
                    throw new IllegalArgumentException("No ratings found for this book");
                }
                return avg;
            }

            public Optional<BookEntity> findByIsbn (String isbn){
                return null;
            }

            @Override
            public List<BookEntity> getBooksByGenre (String genre){
                return bookRepository.findByGenre(genre);
            }

            public List<BookEntity> getTopSellingBooks () {
                Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "copiesSold"));
                return bookRepository.findTopSellers(pageable);
            }

            @Override
            public void discountBooksByPublisher ( double discountPercent, PublisherEntity publisher){
                List<BookEntity> books = bookRepository.findByPublisher(publisher);

                for (BookEntity book : books) {
                    double currentPrice = book.getPrice();
                    double discountedPrice = currentPrice - (currentPrice * discountPercent / 100);

                    book.setPrice(discountedPrice);
                    bookRepository.save(book);
                }
            }

            public List<BookEntity> findByRatingOrHigher (Long rating){
                return bookRepository.findByRating_RatingValueGreaterThanEqual(rating);
            }
        }
