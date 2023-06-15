package com.geektext.geektext_backend_api.repository;
import com.geektext.geektext_backend_api.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long>{
    List<BookEntity> findByGenre(String genre);
    @Query("SELECT b FROM BookEntity b ORDER BY b.copies_sold DESC")
    List<BookEntity> findTopSellers(Pageable pageable);

    List<BookEntity> findByRatingGreaterThanEqual(double rating);

    List<BookEntity> findByPublisher(String publisher);
}
