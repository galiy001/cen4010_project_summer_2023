package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity, Long> {

    List<CommentsEntity> findByBookIsbn(String isbn);
}
