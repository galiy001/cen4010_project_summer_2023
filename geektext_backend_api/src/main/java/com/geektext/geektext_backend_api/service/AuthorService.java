package com.geektext.geektext_backend_api.service;

import com.geektext.geektext_backend_api.entity.AuthorEntity;
import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    AuthorEntity addAuthor(AuthorEntity author);

    List<BookEntity> getBooksByAuthorId(Long authorId);


}
