package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.AuthorEntity;
import com.geektext.geektext_backend_api.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    @Query("SELECT b FROM BookEntity b WHERE b.author.id = :authorId")
    List<BookEntity> findBooksByAuthorId(@Param("authorId") Long authorId);
}

