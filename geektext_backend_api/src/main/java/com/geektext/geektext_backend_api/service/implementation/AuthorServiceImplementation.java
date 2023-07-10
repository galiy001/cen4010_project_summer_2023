package com.geektext.geektext_backend_api.service.implementation;

import com.geektext.geektext_backend_api.entity.AuthorEntity;
import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.UserEntity;
import com.geektext.geektext_backend_api.repository.AuthorRepository;
import com.geektext.geektext_backend_api.repository.BookRepository;
import com.geektext.geektext_backend_api.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorEntity addAuthor(AuthorEntity author) {
        return authorRepository.save(author);
    }

    @Override
    public List<BookEntity> getBooksByAuthorId(Long authorId) {
        return authorRepository.findBooksByAuthorId(authorId);
    }
}
