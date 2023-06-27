package com.geektext.geektext_backend_api.service.implementation;


import com.geektext.geektext_backend_api.service.BookService;
import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.RatingsEntity;
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
    public Optional<BookEntity> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void addBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    @Override
    public void updateBook(String isbn, BookEntity bookEntity) {
        Optional<BookEntity> optionalEntity = bookRepository.findByIsbn(isbn);
        if (optionalEntity.isPresent()) {
            BookEntity existingEntity = optionalEntity.get();
            // Assuming all fields should be updated. Adjust as needed.
            existingEntity.setName(bookEntity.getName());
            existingEntity.setAuthor_id(bookEntity.getAuthor_id());
            existingEntity.setGenre(bookEntity.getGenre());
            existingEntity.setPublisher_id(bookEntity.getPublisher_id());
            existingEntity.setIsbn(bookEntity.getIsbn());
            bookRepository.save(existingEntity);
        } else {
            throw new RuntimeException("Book with ISBN " + isbn + " not found.");
        }
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
    public void discountBooksByPublisher(double discountPercent, int publisher_id) {
        List<BookEntity> books = bookRepository.findByPublisher_Id(publisher_id);

        for (BookEntity book : books) {
            double currentPrice = book.getPrice();
            double discountedPrice = currentPrice - (currentPrice * discountPercent / 100);

            book.setPrice(discountedPrice);
            bookRepository.save(book);
        }
    }
    
	@Override
	public List<BookEntity> findByRatingOrHigher(Long rating) {
		return bookRepository.findByRatingOrHigher(rating);
	}

}
