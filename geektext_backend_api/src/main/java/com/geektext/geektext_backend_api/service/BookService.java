package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.CommentsEntity;
import com.geektext.geektext_backend_api.entity.RatingsEntity;
import com.geektext.geektext_backend_api.repository.BookRepository;
import com.geektext.geektext_backend_api.repository.CommentRepository;
import com.geektext.geektext_backend_api.repository.RatingRepository;
import com.geektext.geektext_backend_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.geektext.geektext_backend_api.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository, CommentRepository commentRepository, RatingRepository ratingRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<BookEntity> getAllBooks(){
        return bookRepository.findAll();
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

    public void commentBook(String isbn, Long userId, String comment){
        BookEntity book = getBookByIsbn(isbn);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if(book != null){
            CommentsEntity newComment = new CommentsEntity(comment, user, book);
            commentRepository.save(newComment);
        } else {
            throw new IllegalArgumentException("Book not found.");
        }
    }


    public Double getAverageRatingForBook(String isbn){
        Double avg = ratingRepository.findAverageRatingByBookId(isbn);
        if(avg == null){
            throw new IllegalArgumentException("No ratings found for this book");
        }
        return avg;
    }
}
