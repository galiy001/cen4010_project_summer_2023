package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.CommentsEntity;
import com.geektext.geektext_backend_api.entity.RatingsEntity;
import com.geektext.geektext_backend_api.entity.UserEntity;
import org.springframework.stereotype.Service;
List;
import java.util.Optional;

@Service
public interface BookService {

    List<BookEntity> getAllBooks();

    BookEntity getBookByIsbn(String isbn);

    List<CommentsEntity> getCommentsForBook(String isbn);

    void rateBook(String isbn, Long userId, Long rating);

    void commentBook(String isbn, Long userId, String comment);

    Double getAverageRatingForBook(String isbn);

    Optional<BookEntity> findByIsbn(String isbn);

    public Optional<BookEntity> findBookByIsbn(String isbn);

    void addBook(BookEntity bookEntity);

    void updateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> getBooksByGenre(String genre);

    List<BookEntity> getTopSellingBooks();

    public void discountBooksByPublisher(double discountPercent, int publisher_id);

    public List<BookEntity> findByRatingOrHigher(Long rating);
	
}
