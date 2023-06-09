package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.CommentsEntity;
import com.geektext.geektext_backend_api.entity.PublisherEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
This interface defines the service layer for book-related operations, including those related to comments and ratings.
It declares methods to fetch all comments for a book, to add a rating for a book, to add a comment for a book, and to fetch the average rating for a book.
*/
@Service
public interface BookService {

    List<BookEntity> getAllBooks();

    BookEntity getBookByIsbn(String isbn);

    List<CommentsEntity> getCommentsForBook(String isbn);

    void rateBook(String isbn, Long userId, Long rating);

    void commentBook(String isbn, Long userId, String comment);

    Double getAverageRatingForBook(String isbn);

    Optional<BookEntity> findByIsbn(String isbn);

    Optional<BookEntity> findBookByIsbn(String isbn);

    BookEntity addBook(BookEntity bookEntity);

    void updateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> getBooksByGenre(String genre);

    List<BookEntity> getTopSellingBooks();

    void discountBooksByPublisher(Double discountPercent, PublisherEntity publisher);

    List<BookEntity> findByRatingOrHigher(Long rating);

    String getBookDescriptionByIsbn(String isbn);
}
