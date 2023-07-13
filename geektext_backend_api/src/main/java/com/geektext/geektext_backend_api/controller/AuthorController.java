package com.geektext.geektext_backend_api.controller;

import com.geektext.geektext_backend_api.entity.AuthorEntity;
import com.geektext.geektext_backend_api.entity.BookEntity;
import com.geektext.geektext_backend_api.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorEntity> createAuthor(@RequestBody AuthorEntity authorEntity) {
        if (authorEntity.getFirstName() == null || authorEntity.getLastName() == null || authorEntity.getBiography() == null || authorEntity.getPublisher() == null ){  //Method for adding a user to the Database
            return ResponseEntity.badRequest().build();
        }

        AuthorEntity newAuthor = new AuthorEntity();
        newAuthor.setFirstName(authorEntity.getFirstName());
        newAuthor.setLastName(authorEntity.getLastName());
        newAuthor.setBiography(authorEntity.getBiography());
        newAuthor.setPublisher(authorEntity.getPublisher());

        newAuthor.setAuthorName(authorEntity.getAuthorName() != null ? authorEntity.getAuthorName() : "");

        AuthorEntity savedUser = authorService.addAuthor(newAuthor);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/{authorId}")
    public List<BookEntity> getBooksByAuthorId(@PathVariable Long authorId) { // Method for retrieving all  book associated with a book Id
        return authorService.getBooksByAuthorId(authorId);
    }


}
