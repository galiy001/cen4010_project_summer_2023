package com.geektext.geektext_backend_api.service.implementation;


import com.geektext.geektext_backend_api.service.BookService;
import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookEntity getBookById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public void addBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    @Override
    public void updateBook(Long id, BookEntity bookEntity) {
        Optional<BookEntity> optionalEntity = bookRepository.findById(id);
        if (optionalEntity.isPresent()) {
            BookEntity existingEntity = optionalEntity.get();
            // Assuming all fields should be updated. Adjust as needed.
            existingEntity.setTitle(bookEntity.getTitle());
            existingEntity.setAuthor(bookEntity.getAuthor());
            existingEntity.setGenre(bookEntity.getGenre());
            existingEntity.setPublished_date(bookEntity.getPublished_date());
            existingEntity.setIsbn(bookEntity.getIsbn());
            bookRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Book with id " + id + " not found.");
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookEntity> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<BookEntity> getTopSellingBooks() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "copies_sold"));
        return bookRepository.findTopSellers(pageable);
    }

    @Override
    public List<BookEntity> getBooksByRating(double rating) {
        return bookRepository.findByRatingGreaterThanEqual(rating);
    }

    @Override
    public void discountBooksByPublisher(double discountPercent, String publisher) {
        List<BookEntity> books = bookRepository.findByPublisher(publisher);

        for (BookEntity book : books) {
            double currentPrice = book.getPrice();
            double discountedPrice = currentPrice - (currentPrice * discountPercent / 100);

            book.setPrice(discountedPrice);
            bookRepository.save(book);
        }
    }

}
