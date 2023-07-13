package com.geektext.geektext_backend_api.repository;

import com.geektext.geektext_backend_api.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
This interface extends JpaRepository to provide database operations related to the Comment entity.
In particular, it includes a method to fetch all comments associated with a particular book using its ISBN.
*/
@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity, Long> {

    List<CommentsEntity> findByBookIsbn(String isbn);
}
