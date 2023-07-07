package com.geektext.geektext_backend_api.service;
import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.RatingsEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookEntity> getAllBooks();
    public Optional<BookEntity> findBookByIsbn(String isbn);
    void addBook(BookEntity bookEntity);
    void updateBook(String isbn, BookEntity bookEntity);


    

    List<BookEntity> getBooksByGenre(String genre);

    List<BookEntity> getTopSellingBooks();

    public void discountBooksByPublisher(double discountPercent, int publisher_id);

    public List<BookEntity> findByRatingOrHigher(Long rating);
	
}
