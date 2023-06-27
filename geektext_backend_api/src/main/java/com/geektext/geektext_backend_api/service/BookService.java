package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.BookEntity;

import java.util.Optional;

public interface BookService {
    Optional<BookEntity> findBookByIsbn(String isbn);

}
