package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
