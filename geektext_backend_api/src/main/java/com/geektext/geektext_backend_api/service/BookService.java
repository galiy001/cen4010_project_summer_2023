package com.geektext.geektext_backend_api.service;
import com.geektext.geektext_backend_api.entity.BookEntity;
import java.util.List;

public interface BookService {
    List<BookEntity> getAllBooks();
    BookEntity getBookById(Long id);
    void addBook(BookEntity bookEntity);
    void updateBook(Long id, BookEntity bookEntity);
    void deleteBook(Long id);

    List<BookEntity> getBooksByGenre(String genre);

    List<BookEntity> getTopSellingBooks();

    List<BookEntity> getBooksByRating(double rating);

    public void discountBooksByPublisher(double discountPercent, String publisher);
}
