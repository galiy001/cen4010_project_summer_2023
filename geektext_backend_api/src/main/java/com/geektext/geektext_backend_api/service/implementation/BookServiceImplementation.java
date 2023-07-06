package com.geektext.geektext_backend_api.service.implementation;

import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.service.BookService;
import com.geektext.geektext_backend_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<BookEntity> findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

}
